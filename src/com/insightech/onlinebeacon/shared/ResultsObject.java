package com.insightech.onlinebeacon.shared;

import java.io.Serializable;

public class ResultsObject implements Serializable {
	private static final long serialVersionUID = 1L;
	private long classNumber;
	private long gameNumber;
	private int teamIndex;
	private int yearIndex;
	private String companyName;
	private long revenue;
	private long cogs;
	private long expenses;
	private long interest;
	private long taxes;
	private long cash;
	private long inventory;
	private long plant;
	private long accumulatedDepreciation;
	private long assets;
	private long loan;
	private long sharesOutstanding;
	private long retainedEarnings;
	private long startingCash;
	private long inventoryDelta;
	private long loanDelta;
	private long depreciationExpense;
	private MfgProductResults alphaProduct;
	private MfgProductResults betaProduct;
	private MfgProductResults chiProduct;
	private SigmaResults sigmaProduct;
	private long loanLimit;
	private float betaCompletePercent;
	private String chiRating;
	private String betaRating;
	private long investments;
	private long stockPrice;

	@SuppressWarnings("unused")
	private ResultsObject() {
	}

	public ResultsObject(long cId, long gId, int year, int team, String name) {
		classNumber = cId;
		gameNumber = gId;
		teamIndex = team;
		yearIndex = year;
		companyName = name;
	}

	public Long getSessionNumber() {
		return classNumber;
	}

	public int getTeamIndex() {
		return teamIndex;
	}

	public int getYearIndex() {
		return yearIndex;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setIncomeStatement(long r, long c, long e, long i, long t) {
		revenue = r;
		cogs = c;
		expenses = e;
		interest = i;
		taxes = t;
	}

	public long getRevenue() {
		return revenue;
	}

	public long getCogs() {
		return cogs;
	}

	public long getExpenses() {
		return expenses;
	}

	public long getInterest() {
		return interest;
	}

	public long getTaxes() {
		return taxes;
	}

	public long getGrossProfit() {
		return getRevenue() - getCogs();
	}

	public long getOperatingProfit() {
		return getGrossProfit() - getExpenses();
	}

	public long getNetProfit() {
		return getOperatingProfit() - getInterest() - getTaxes();
	}

	public void setAssets(long endingCashBalance, long endingInventoryDollars,
			long endingGrossDepreciableAssetDollars,
			long endingAccumulatedDepreciation, long endingTotalAssets) {
		cash = endingCashBalance;
		inventory = endingInventoryDollars;
		plant = endingGrossDepreciableAssetDollars;
		accumulatedDepreciation = endingAccumulatedDepreciation;
		assets = endingTotalAssets;
	}

	public long getCash() {
		return cash;
	}

	public long getInventory() {
		return inventory;
	}

	public long getPlant() {
		return plant;
	}

	public long getAccumulatedDepreciation() {
		return accumulatedDepreciation;
	}

	public long getAssets() {
		return assets;
	}

	public long getNetPlant() {
		return getPlant() - getAccumulatedDepreciation();
	}

	public void setLiabilities(long endingLoan, long limit, long shares,
			long endingRetainedEarnings) {
		loan = endingLoan;
		sharesOutstanding = shares;
		retainedEarnings = endingRetainedEarnings;
		loanLimit = limit;
	}

	public long getLoan() {
		return loan;
	}

	public long getSharesOutstanding() {
		return sharesOutstanding;
	}

	public long getRetainedEarnings() {
		return retainedEarnings;
	}

	public void setCashFlow(long startingCashBalance, long inventory,
			long loan, long depreciation, long invest) {
		startingCash = startingCashBalance;
		inventoryDelta = inventory;
		loanDelta = loan;
		depreciationExpense = depreciation;
		investments = invest;
	}

	public long getStartingCash() {
		return startingCash;
	}

	public long getInventoryDelta() {
		return inventoryDelta;
	}

	public long getLoanDelta() {
		return loanDelta;
	}

	public long getDepreciationExpense() {
		return depreciationExpense;
	}

	public void setAlpha(MfgProductResults alpha) {
		alphaProduct = alpha;
	}

	public MfgProductResults getAlphaProduct() {
		return alphaProduct;
	}

	public void setBeta(MfgProductResults beta) {
		betaProduct = beta;
	}

	public MfgProductResults getBetaProduct() {
		return betaProduct;
	}

	public void setChi(MfgProductResults chi) {
		chiProduct = chi;
	}

	public MfgProductResults getChiProduct() {
		return chiProduct;
	}

	public void setSigma(SigmaResults sigma) {
		sigmaProduct = sigma;
	}

	public SigmaResults getSigmaProduct() {
		return sigmaProduct;
	}

	public long getLoanLimit() {
		return loanLimit;
	}

	public void setBetaCompletePercent(float complete) {
		betaCompletePercent = complete;
	}

	public float getBetaCompletePercent() {
		return betaCompletePercent;
	}

	public void setChiRating(String starRating) {
		chiRating = starRating;
	}

	public void setBetaRating(String starRating) {
		betaRating = starRating;
	}

	public String getChiRating() {
		return chiRating;
	}

	public String getBetaRating() {
		return betaRating;
	}

	public long getInvestments() {
		return investments;
	}

	public void setInvestments(long investments) {
		this.investments = investments;
	}

	public Long getGameNumber() {
		return gameNumber;
	}

	public void setStockPrice(long sp) {
		stockPrice = sp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getClassNumber() {
		return classNumber;
	}

	public long getStockPrice() {
		return stockPrice;
	}

}
