package com.insightech.onlinebeacon.shared;

import java.io.Serializable;

public class MfgProductResults extends CommonProductResults implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private MfgProductResults() {
	}

	private long startingUnits;
	private long production;
	private float productionUnitCost;
	private long capacity;
	private long depreciation;
	private float variableCostPerUnit;
	private float startingInventoryUnitCost;
	private long overhead;
	private float capacityPerDollar;
	private float variableCostPerUnitNextYear;

	public MfgProductResults(String name) {
		productName = name;
	}

	public void setSupply(long startingInventoryUnits, long plan,
			long startingCapacity, float cpd) {
		startingUnits = startingInventoryUnits;
		production = plan;
		capacity = startingCapacity;
		capacityPerDollar = cpd;
	}

	public long getStartingUnits() {
		return startingUnits;
	}

	public long getEndingUnits() {
		return getSupply() - getUnitsSold();
	}

	public long getProduction() {
		return production;
	}

	public long getSupply() {
		return getStartingUnits() + getProduction();
	}

	public void setCost(long dep, float vc, long over, float siuc, float vcny) {
		depreciation = dep;
		variableCostPerUnit = vc;
		startingInventoryUnitCost = siuc;
		overhead = over;
		variableCostPerUnitNextYear = vcny;
	}

	public float getProductionUnitCost() {
		return productionUnitCost;
	}

	public long getCapacity() {
		return capacity;
	}

	public long getDepreciation() {
		return depreciation;
	}

	public long getInventoryCost() {
		return (long) (getStartingUnits() * getStartingInventoryUnitCostThisYear());
	}

	public float getVariableCostPerUnit() {
		return variableCostPerUnit;
	}

	public long getTotalVariableCost() {
		return (long) (getVariableCostPerUnit() * getProduction());
	}

	public float getStartingInventoryUnitCostThisYear() {
		return startingInventoryUnitCost;
	}

	public long getOverhead() {
		return overhead;
	}

	public long getTotalFixedCost() {
		return getOverhead() + getDepreciation();
	}

	public long getTotalCost() {
		return getTotalFixedCost() + getTotalVariableCost()
				+ getInventoryCost();
	}

	public float getUnitCost() {
		return getSupply() > 0 ? getTotalCost() / getSupply() : 0;
	}

	public Long getExpenses() {
		return getMarketing() + getDevelopment();
	}

	public Long getProductionCost() {
		return getTotalFixedCost() + getTotalVariableCost();
	}

	public float getCapacityPerDollar() {
		return capacityPerDollar;
	}

	@Override
	public long getCogs() {
		if (cogs > 0)
			return cogs;
		return depreciation + overhead;
	}

	@Override
	public long getPlan() {
		return production;
	}

	public float getStartingInventoryUnitCost() {
		return startingInventoryUnitCost;
	}

	public float getVariableCostPerUnitNextYear() {
		return variableCostPerUnitNextYear;
	}

}
