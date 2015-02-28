package com.insightech.onlinebeacon.shared;

import java.io.Serializable;

public class MfgProductDecision extends CommonProductDecision implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private MfgProductDecision() {
		super();
	}

	private MfgProductResults mfgResults;
	private long capEx = 0;

	public MfgProductDecision(MfgProductResults results) {
		super(results);
		mfgResults = results;
	}

	public MfgProductResults getMfgResults() {
		return mfgResults;
	}

	public long getSupply() {
		return getPlan() + getInventory();
	}

	public long getInventory() {
		return getMfgResults().getEndingUnits();
	}

	public long getExpectedRevenue() {
		return getSupply() * getPrice();
	}

	public long getProductionCost() {
		return getTotalVariableCost() + mfgResults.getTotalFixedCost();
	}

	protected long getTotalVariableCost() {
		return (long) (getPlan() * mfgResults.getVariableCostPerUnit());
	}

	public long getCOGS() {
		return mfgResults.getInventoryCost() + getProductionCost();
	}

	public long getExpectedCashUsage() {
		return getMarketing() + getDevelopment() + getCapEx();
	}

	public float getCapacityPerDollar() {
		return mfgResults.getCapacityPerDollar();
	}

	public void setCapEx(long value) {
		capEx = value;
	}

	public long getCapEx() {
		return capEx;
	}

}
