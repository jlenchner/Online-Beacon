package com.insightech.onlinebeacon.shared;

import java.io.Serializable;
import java.util.Date;

public class DecisionObject implements Serializable, Comparable<DecisionObject> {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private DecisionObject() {
	}

	private ResultsObject result;
	private MfgProductDecision alpha;
	private MfgProductDecision beta;
	private MfgProductDecision chi;
	private SigmaDecision sigma;
	Date lastUpdate;

	public DecisionObject(ResultsObject results, Date createDate) {
		result = results;
		initializeProducts(results);
		lastUpdate = createDate;
	}

	public void initializeProducts(ResultsObject results) {
		alpha = new MfgProductDecision(results.getAlphaProduct());
		beta = new MfgProductDecision(results.getBetaProduct());
		chi = new MfgProductDecision(result.getChiProduct());
		sigma = new SigmaDecision(results.getSigmaProduct());
	}

	private Long loanRequest = 0L;
	private Long loanPayment = 0L;

	public Long getSessionNumber() {
		return result.getSessionNumber();
	}

	public ResultsObject getResults() {
		return result;
	}

	public MfgProductDecision getAlphaDecision() {
		return alpha;
	}

	public long getExpectedTotalRevenue() {
		return alpha.getExpectedRevenue() + beta.getExpectedRevenue()
				+ chi.getExpectedRevenue() + sigma.getExpectedRevenue();
	}

	public String getTeam() {
		return result.getCompanyName();
	}

	public MfgProductDecision getBetaDecision() {
		return beta;
	}

	public MfgProductDecision getChiDecision() {
		return chi;
	}

	public SigmaDecision getSigmaDecision() {
		return sigma;
	}

	public int getYearIndex() {
		return result.getYearIndex() + 1;
	}

	public int getDecisionIndex() {
		return getYearIndex();
	}

	public long getExpectedCOGS() {
		return alpha.getCOGS() + beta.getCOGS() + chi.getCOGS()
				+ sigma.getWorkingContractorCost();
	}

	public long getExpectedGrossProfit() {
		return getExpectedTotalRevenue() - getExpectedCOGS();
	}

	public long getExpectedCashUsage() {
		return alpha.getExpectedCashUsage() + beta.getExpectedCashUsage()
				+ chi.getExpectedCashUsage() + sigma.getExpectedCashUsage();
	}

	public long getExpectedOperatingProfit() {
		return getExpectedGrossProfit() - getExpectedCashUsage();

	}

	public long getInterest() {
		return (long) (result.getLoan() * .06);
	}

	public long getTax() {
		return (long) ((getExpectedOperatingProfit() - getInterest()) * .22);
	}

	public long getExpectedNetProfit() {
		return getExpectedOperatingProfit() - getInterest() - getTax();
	}

	public long getAvailableCash() {
		return result.getCash() - getExpectedCashUsage() - getLoanPayment();
	}

	public void setLoanRequest(Long value) {
		loanRequest = value;
	}

	public void setPaymentRequest(Long value) {
		loanPayment = value;
	}

	public Long getLoanRequest() {
		return loanRequest;
	}

	public Long getLoanPayment() {
		return loanPayment;
	}

	public void refresh() {
		initializeProducts(result);
	}

	public int getTeamIndex() {
		return result.getTeamIndex();
	}

	public int compareTo(DecisionObject o) {
		if (getYearIndex() == o.getYearIndex()) {
			return compareInts(getTeamIndex(), o.getTeamIndex());
		}
		return compareInts(getYearIndex(), o.getYearIndex());
	}

	public int compareInts(int x, int y) {
		return x > y ? 1 : x < y ? -1 : 0;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
