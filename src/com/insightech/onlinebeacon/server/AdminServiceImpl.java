package com.insightech.onlinebeacon.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.insightech.beacon.simulator.AbstractMfgProductBooks;
import com.insightech.beacon.simulator.AbstractProductBooks;
import com.insightech.beacon.simulator.AlphaBooks;
import com.insightech.beacon.simulator.BetaBooks;
import com.insightech.beacon.simulator.ChiBooks;
import com.insightech.beacon.simulator.CompanyBooks;
import com.insightech.beacon.simulator.SigmaBooks;
import com.insightech.beacon.simulator.Simulator;
import com.insightech.onlinebeacon.admin.AdminService;
import com.insightech.onlinebeacon.admin.reports.TeamSummaryObject;
import com.insightech.onlinebeacon.server.records.GameRecord;
import com.insightech.onlinebeacon.server.records.TeamRecord;
import com.insightech.onlinebeacon.shared.ClassObject;
import com.insightech.onlinebeacon.shared.DecisionObject;
import com.insightech.onlinebeacon.shared.GameStatusObject;
import com.insightech.onlinebeacon.shared.MfgProductResults;
import com.insightech.onlinebeacon.shared.PingRecord;
import com.insightech.onlinebeacon.shared.ProductSummaryElement;
import com.insightech.onlinebeacon.shared.ResultsCollection;
import com.insightech.onlinebeacon.shared.ResultsObject;
import com.insightech.onlinebeacon.shared.SigmaProductSummaryElement;
import com.insightech.onlinebeacon.shared.SigmaResults;

public class AdminServiceImpl extends RemoteServiceServlet implements
		AdminService {

	private static final long serialVersionUID = 1L;
	private PersistenceManager pm;
	private static final Logger log = Logger.getLogger(AdminServiceImpl.class
			.getName());

	// TODO add logging

	public AdminServiceImpl() {
	}

	@Override
	public int ping() {

		System.out.println("ping");

		pm = PMF.get().getPersistenceManager();
		PingRecord pr = new PingRecord();
		try {
			pm.makePersistent(pr);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pm.close();
		}

		Query query = PMF.get().getPersistenceManager()
				.newQuery(PingRecord.class);
		@SuppressWarnings("unchecked")
		List<PingRecord> result = (List<PingRecord>) query.execute();
		System.out.println(result.size());
		return result.size();

	}

	@Override
	public List<ClassObject> saveClassObject(ClassObject classObject) {
		classObject.setLastUpdate(new Date());
		pm = PMF.get().getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		if (classObject.getClassNumber() == null) {
			try {
				tx.begin();
				pm.makePersistent(classObject);
				tx.commit();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (tx.isActive())
					tx.rollback();
				pm.close();
			}
		} else {
			try {
				tx.begin();
				ClassObject dbClassObject = pm.getObjectById(ClassObject.class,
						classObject.getClassNumber());
				dbClassObject.update(classObject);
				tx.commit();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (tx.isActive())
					tx.rollback();
				pm.close();
			}
		}

		return getClassObjectList();
	}

	@Override
	public ClassObject getClassObject(Long classNumber) {
		pm = PMF.get().getPersistenceManager();
		ClassObject classObject = null;
		try {
			classObject = pm.getObjectById(ClassObject.class, classNumber);
			classObject = pm.detachCopy(classObject);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pm.close();
		}
		return classObject;
	}

	@Override
	public List<ClassObject> deleteClassFromDatabase(final Long classNumber) {
		pm = PMF.get().getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Key coKey = KeyFactory.createKey(ClassObject.class.getSimpleName(),
					classNumber);
			ClassObject coToDelete = pm.getObjectById(ClassObject.class, coKey);
			List<Integer> gameCountList = coToDelete.getTeamCountList();
			for (int game = 1; game <= gameCountList.size(); game++) {
				Key gameKey = KeyFactory.createKey(coKey,
						GameRecord.class.getSimpleName(), game);
				GameRecord go = pm.getObjectById(GameRecord.class, gameKey);
				for (int team = 1; team <= gameCountList.get(game - 1); team++) {
					Key teamKey = KeyFactory.createKey(gameKey,
							TeamRecord.class.getSimpleName(), team);
					TeamRecord to = pm.getObjectById(TeamRecord.class, teamKey);
					pm.deletePersistent(to);
				}
				pm.deletePersistent(go);
			}
			pm.deletePersistent(coToDelete);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (tx.isActive())
				tx.rollback();
			pm.close();
		}

		return getClassObjectList();
	}

	@Override
	public List<ClassObject> getClassObjectList() {

		List<ClassObject> classList = new ArrayList<ClassObject>();
		pm = PMF.get().getPersistenceManager();
		try {
			Extent<ClassObject> extent = pm.getExtent(ClassObject.class);
			for (ClassObject classObject : extent) {
				classList.add(pm.detachCopy(classObject));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pm.close();
		}

		return classList;
	}

	@Override
	public void startClass(Long classNumber) {
		pm = PMF.get().getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		// TODO add time class started.
		try {
			tx.begin();
			Key coKey = KeyFactory.createKey(ClassObject.class.getSimpleName(),
					classNumber);
			ClassObject classObject = pm
					.getObjectById(ClassObject.class, coKey);
			List<Integer> teamCountList = classObject.getTeamCountList();
			for (int game = 0; game < teamCountList.size(); game++) {
				Integer teams = teamCountList.get(game);
				int gameNumber = game + 1;
				GameRecord gameRecord = new GameRecord(classObject, gameNumber,
						teams, coKey);
				pm.makePersistent(gameRecord);
				Simulator sim = gameRecord.getSim();
				int yearIndex = sim.getCurrentYearIndex();
				for (int team = 0; team < teams; team++) {
					int teamNumber = team + 1;
					TeamRecord teamObject = new TeamRecord(gameRecord,
							teamNumber, gameRecord.getGameKey());
					CompanyBooks books = sim.getCurrentCompanyBooks(team);
					ResultsObject resultsObject = createResultsObject(
							classNumber, gameNumber, teamNumber, yearIndex,
							books);
					teamObject.addResultsObject(resultsObject);
					DecisionObject decisionObject = new DecisionObject(
							resultsObject, new Date());
					teamObject.addDecisionObject(decisionObject);
					pm.makePersistent(teamObject);
				}
			}
			classObject.setSimulationStartDate(new Date());
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (tx.isActive())
				tx.rollback();
			pm.close();
		}

	}

	public ResultsObject createResultsObject(Long sessionNumber,
			long gameNumber, int teamIndex, int yearIndex, CompanyBooks books) {

		ResultsObject results = new ResultsObject(sessionNumber, gameNumber,
				yearIndex, teamIndex, books.getCompanyName());

		transferCompanyResults(books, results);

		MfgProductResults alpha = new MfgProductResults("Alpha");

		transferResults(
				(AbstractMfgProductBooks) books
						.getProductBooks(AlphaBooks.class),
				alpha);

		results.setAlpha(alpha);

		MfgProductResults beta = new MfgProductResults("Beta");

		BetaBooks betaBooks = (BetaBooks) books
				.getProductBooks(BetaBooks.class);

		transferResults(betaBooks, beta);

		results.setBetaCompletePercent(betaBooks.developmentCompletePercent());

		results.setBetaRating(starRating(betaBooks.getQualityRating()));

		results.setBeta(beta);

		MfgProductResults chi = new MfgProductResults("Chi");

		ChiBooks chiBooks = (ChiBooks) books.getProductBooks(ChiBooks.class);

		results.setChiRating(starRating(chiBooks.getQualityRating()));

		transferResults(chiBooks, chi);

		results.setChi(chi);

		SigmaResults sigma = new SigmaResults();

		SigmaBooks sigmaBooks = (SigmaBooks) books
				.getProductBooks(SigmaBooks.class);

		sigma.setSigmaPAndL(sigmaBooks.getRetailPrice(), sigmaBooks
				.getOrderBook().getSold(), sigmaBooks.getMarketingDollars(),
				sigmaBooks.getDevelopmentDollars(), sigmaBooks
						.getSeverancePayDollars(), sigmaBooks.getOrderBook()
						.getLostOrders());

		sigma.setContractors(sigmaBooks.getContractors(),
				sigmaBooks.getInitialExperiencePerContractor(),
				sigmaBooks.getEndingExperiencePerContractor());

		List<SigmaProductSummaryElement> list = new ArrayList<SigmaProductSummaryElement>();

		for (AbstractProductBooks sigmaProductBook : sigmaBooks.getMyIndustry()
				.getCurrentProductBooksArray()) {
			SigmaBooks thisBooks = (SigmaBooks) sigmaProductBook;
			list.add(new SigmaProductSummaryElement(thisBooks.getCompanyName(),
					thisBooks.getRetailPrice(), thisBooks.getOrderBook()
							.getSold(), thisBooks.getContractors(), thisBooks
							.getGrossProfit(), thisBooks.getOperatingProfit()));
		}

		sigma.setSigmaCompetitiveList(list);

		results.setSigma(sigma);
		return results;
	}

	private String starRating(float qualityRating) {
		if (qualityRating == 0)
			return "     ";
		if (qualityRating <= 1f)
			return "*    ";
		if (qualityRating <= 2f)
			return "**   ";
		if (qualityRating <= 3f)
			return "*** ";
		if (qualityRating <= 4f)
			return "****";
		return "*****";
	}

	protected void transferCompanyResults(CompanyBooks books,
			ResultsObject results) {

		results.setStockPrice(books.getStockPrice());

		results.setIncomeStatement(books.getRevenue(),
				books.getCostOfGoodsSold(), books.getExpenses(),
				books.getInterestExpense(), books.getTaxes());

		results.setAssets(books.getEndingCashBalance(),
				books.getEndingInventoryDollars(),
				books.getEndingGrossDepreciableAssetDollars(),
				books.getEndingAccumulatedDepreciation(),
				books.getEndingTotalAssets());

		results.setLiabilities(books.getEndingLoan(), books.getLoanLimit(),
				books.getShares(), books.getEndingRetainedEarnings());

		long loanPayment = books.getInitialLoan() - books.getStartingLoan();
		long loanRequest = books.getEndingLoan() - books.getStartingLoan();
		long loanDelta = (loanRequest > 0) ? loanRequest : -loanPayment;

		results.setCashFlow(
				books.getStartingCashBalance(),
				books.getStartingInventoryDollars()
						- books.getEndingInventoryDollars(), loanDelta,
				books.getPlantYearlyDepreciation(), books.getInvestments());
	}

	protected void transferResults(AbstractMfgProductBooks mfgBooks,
			MfgProductResults mfgResults) {

		mfgResults.setPAndL(mfgBooks.getOrderBook().getSold(), mfgBooks
				.getRetailPrice(), mfgBooks.getCostOfGoodsSold(), mfgBooks
				.getDevelopmentDollars(), mfgBooks.getMarketingDollars(),
				mfgBooks.getOrderBook().getLostOrders());

		int aBillion = 1000000000;
		float capacityPerMillionDollars = mfgBooks
				.getCapacityPerGrossPlantDollar(aBillion);
		float capacityPerDollar = capacityPerMillionDollars / aBillion;

		mfgResults.setSupply(mfgBooks.getStartingInventoryUnits(),
				mfgBooks.getProduction(), mfgBooks.getEndingPlantCapacity(),
				capacityPerDollar);

		mfgResults.setCost(mfgBooks.getDepreciation(),
				mfgBooks.getVariableCostPerUnit(), mfgBooks.getOverhead(),
				mfgBooks.getStartingInventoryUnitCost(),
				mfgBooks.getVariableCostPerUnitNextYear());

		List<ProductSummaryElement> list = new ArrayList<ProductSummaryElement>();

		for (AbstractProductBooks abstractProductBook : mfgBooks
				.getMyIndustry().getCurrentProductBooksArray()) {
			AbstractMfgProductBooks thisBooks = (AbstractMfgProductBooks) abstractProductBook;
			list.add(new ProductSummaryElement(thisBooks.getCompanyName(),
					thisBooks.getRetailPrice(), thisBooks.getOrderBook()
							.getSold(), thisBooks.getEndingInventoryDollars(),
					thisBooks.getGrossProfit(), thisBooks.getOperatingProfit(),
					thisBooks.getEndingPlantCapacity()));
		}

		mfgResults.setCompetitiveList(list);
	}

	@Override
	public List<GameStatusObject> getGameSummaryObjectList(Long classNumber) {
		pm = PMF.get().getPersistenceManager();
		List<GameStatusObject> summaryList = new ArrayList<GameStatusObject>();
		try {
			ClassObject co = pm.getObjectById(ClassObject.class, classNumber);
			Key coKey = KeyFactory.createKey(ClassObject.class.getSimpleName(),
					co.getClassNumber());
			for (int game = 1; game <= co.getGames(); game++) {
				Key gameKey = KeyFactory.createKey(coKey,
						GameRecord.class.getSimpleName(), game);
				GameRecord gr = pm.getObjectById(GameRecord.class, gameKey);
				GameStatusObject gso = gr.getSummary();
				summaryList.add(gso);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pm.close();
		}
		return summaryList;
	}

	@Override
	public List<TeamSummaryObject> getTeamSummaryObjectList(Long classNumber,
			Long gameNumber) {
		pm = PMF.get().getPersistenceManager();
		List<TeamSummaryObject> list = new ArrayList<TeamSummaryObject>();
		try {
			ClassObject co = pm.getObjectById(ClassObject.class, classNumber);
			Key coKey = KeyFactory.createKey(ClassObject.class.getSimpleName(),
					co.getClassNumber());
			Key gameKey = KeyFactory.createKey(coKey,
					GameRecord.class.getSimpleName(), gameNumber);
			GameRecord gr = pm.getObjectById(GameRecord.class, gameKey);
			for (int team = 1; team <= gr.getTeamCount(); team++) {
				Key teamKey = KeyFactory.createKey(gameKey,
						TeamRecord.class.getSimpleName(), team);
				TeamRecord tr = pm.getObjectById(TeamRecord.class, teamKey);
				list.add(tr.getTeamSummaryObject());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pm.close();
		}
		return list;
	}

	@Override
	public ResultsCollection getResults(Long classNumber, Long gameNumber) {
		pm = PMF.get().getPersistenceManager();
		ResultsCollection resultsCollection = null;
		try {
			ClassObject co = pm.getObjectById(ClassObject.class, classNumber);
			Key coKey = KeyFactory.createKey(ClassObject.class.getSimpleName(),
					co.getClassNumber());
			Key gameKey = KeyFactory.createKey(coKey,
					GameRecord.class.getSimpleName(), gameNumber);
			GameRecord gr = pm.getObjectById(GameRecord.class, gameKey);
			Simulator sim = gr.getSim();
			resultsCollection = new ResultsCollection(classNumber, gameNumber,
					gr.getTeamCount());
			for (int yearIndex = 0; yearIndex <= sim.getCurrentYearIndex(); yearIndex++) {
				for (int company = 0; company < sim.getNumberOfCompanies(); company++) {
					CompanyBooks books = sim
							.getCompanyBooks(company, yearIndex);
					ResultsObject resultsObject = createResultsObject(
							classNumber, gameNumber, company, yearIndex, books);
					resultsCollection.add(resultsObject);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pm.close();
		}
		return resultsCollection;
	}
}
