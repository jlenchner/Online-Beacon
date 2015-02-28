package com.insightech.onlinebeacon.shared;

import java.io.Serializable;
import java.util.List;

import com.insightech.beacon.simulator.SigmaBooks;

public class SigmaResults extends CommonProductResults implements Serializable {

	private static final long serialVersionUID = 1L;
	// private long idleContractorCost;
	private List<SigmaProductSummaryElement> competitiveList;
	private long contractors;
	// private float employed;
	// private float idle;
	// private float contractorsPerContract;
	// private long contractCost;
	private float endingExperience;
	private long severancePay;
	private float startingExperience;

	public SigmaResults() {
		super();
		productName = "Sigma";
	}

	@Override
	public long getCogs() {
		return SigmaCommon.getCostOfContractors(getWorkingContractors());
	}

	public long getContractCost() {
		return SigmaCommon.getCostOfContractors(getContractorsPerContract());
	}

	public long getContractors() {
		return contractors;
	}

	public float getContractorsPerContract() {
		return SigmaCommon.getContractorsPerContract(getStartingExperience());
	}

	public float getEndingContractorsPerContract() {
		return 9 / endingExperience;
	}

	public float getEndingExperience() {
		return endingExperience;
	}

	public long getExpenses() {
		return SigmaCommon.getAdminExpense(getRevenue()) + getMarketing()
				+ getDevelopment() + getSeverancePay()
				+ getIdleContractorCost();
	}

	public long getIdleContractorCost() {
		long idle = (long) (getIdleContractors() * SigmaBooks.YEARLY_SALARY_PER_CONTRACTOR);
		return idle;
	}

	public float getIdleContractors() {
		return getContractors() - getWorkingContractors();
	}

	@Override
	public long getOperatingProfit() {
		return getGrossProfit() - getExpenses();
	}

	// public float getWorkingContractors() {
	// float scpc = SigmaCommon.getContractorsPerContract(startingExperience);
	// long contracts = SigmaCommon.calculateContracts(getContractors(), scpc);
	// contracts = SigmaCommon.calculateContractsFromExperience(
	// getContractors(), startingExperience);
	// return SigmaCommon.workingContractorsFromExperience(contracts, scpc);
	// }

	public long getSeverancePay() {
		return severancePay;
	}

	public List<SigmaProductSummaryElement> getSigmaCompetitiveList() {
		return competitiveList;
	}

	public float getStartingExperience() {
		return startingExperience;
	}

	public float getWorkingContractors() {
		float workers;
		long contracts = getUnitsSold();
		float experience = getStartingExperience();
		workers = SigmaCommon.workingContractorsFromExperience(contracts,
				experience);
		return workers;
	}

	public void setContractors(long contractrs,
			float initialExperiencePerContractor,
			float endingExperiencePerContractor) {
		contractors = contractrs;
		startingExperience = initialExperiencePerContractor;
		endingExperience = endingExperiencePerContractor;
	}

	public void setSigmaCompetitiveList(List<SigmaProductSummaryElement> list) {
		competitiveList = list;
	}

	public void setSigmaPAndL(long retailPrice, long sold,
			long marketingDollars, long developmentDollars,
			long severancePayDollars, long lo) {
		price = retailPrice;
		unitsSold = sold;
		marketing = marketingDollars;
		development = developmentDollars;
		severancePay = severancePayDollars;
		lostOrders = lo;
	}

	@Override
	public long getPlan() {
		return contractors;
	}

}
