package com.insightech.onlinebeacon.shared;

import java.io.Serializable;

import com.insightech.beacon.simulator.SigmaBooks;

public class SigmaDecision extends CommonProductDecision implements
		Serializable {

	private static final long serialVersionUID = 1L;
	private SigmaResults sigmaResults;
	private long contractors;

	@SuppressWarnings("unused")
	private SigmaDecision() {
		super();
	}

	public SigmaDecision(SigmaResults results) {
		super(results);
		sigmaResults = results;
		contractors = sigmaResults.getContractors();
	}

	public SigmaResults getSigmaResults() {
		return sigmaResults;
	}

	public Long getContractors() {
		return contractors;
	}

	public void setContractors(long contractors) {
		this.contractors = contractors;
	}

	public Long getContracts() {
		return (long) Math.floor(contractors / getContractorsPerContract());
	}

	public Long getAdminExpense() {
		long revenue = getRevenue();
		return SigmaCommon.getAdminExpense(revenue);
	}

	public long getRevenue() {
		return getContracts() * getPrice();
	}

	public Long getIdleContractorExpense() {
		return getContractorDollars(getIdleContractors());
	}

	public long getContractorDollars(float contractors) {
		return (long) (contractors * SigmaBooks.YEARLY_SALARY_PER_CONTRACTOR);
	}

	public float getIdleContractors() {
		return contractors - (getContracts() * getContractorsPerContract());
	}

	@Override
	public long getExpectedRevenue() {
		return getPrice() * getContracts();
	}

	public long getPlan() {
		return contractors;
	}

	public long getWorkingContractorCost() {
		return getContractorDollars(getWorkingContractors());
	}

	private float getWorkingContractors() {
		return getContractors() - getIdleContractors();
	}

	public float getContractorsPerContract() {
		return sigmaResults.getEndingContractorsPerContract();
	}

	public float getStartingExperience() {
		return sigmaResults.getEndingExperience();
	}

	public Long getStartingContractors() {
		return sigmaResults.getContractors();
	}

	public long getExpectedCashUsage() {
		return getMarketing() + getDevelopment();
	}

	public void setPlan(long plan) {
		contractors = plan;
	}
}
