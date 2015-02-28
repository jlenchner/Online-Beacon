package com.insightech.onlinebeacon.shared;

import java.io.Serializable;

public class CommonProductDecision implements Serializable {
	private static final long serialVersionUID = 1L;
	private long price;
	private long plan;
	private long marketing;
	private long development;

	protected CommonProductDecision() {

	}

	public CommonProductDecision(CommonProductResults results) {
		price = results.getPrice();
		plan = results.getPlan();
		marketing = results.getMarketing();
		development = results.getDevelopment();
	}

	public long getPrice() {
		return price;
	}

	public long getPlan() {
		return plan;
	}

	public long getExpectedRevenue() {
		return getPrice() * getPlan();
	}

	public long getMarketing() {
		return marketing;
	}

	public long getDevelopment() {
		return development;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public void setPlan(long plan) {
		this.plan = plan;
	}

	public void setMarketing(long marketing) {
		this.marketing = marketing;
	}

	public void setDevelopment(long development) {
		this.development = development;
	}

}
