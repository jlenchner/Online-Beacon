package com.insightech.onlinebeacon.shared;

import java.io.Serializable;
import java.util.List;

public abstract class CommonProductResults implements Serializable {

	private static final long serialVersionUID = 1L;
	protected String productName;
	protected long unitsSold;
	protected long price;
	protected long cogs;
	protected long development;
	protected long marketing;
	protected long lostOrders;
	private List<ProductSummaryElement> competitiveList;

	public String getProductName() {
		return productName;
	}

	public void setPAndL(long sold, long retailPrice, long costOfGoodsSold,
			long developmentDollars, long marketingDollars, long lost) {
		unitsSold = sold;
		price = retailPrice;
		cogs = costOfGoodsSold;
		development = developmentDollars;
		marketing = marketingDollars;
		lostOrders = lost;
	}

	public long getUnitsSold() {
		return unitsSold;
	}

	public long getPrice() {
		return price;
	}

	public long getCogs() {
		return cogs;
	}

	public long getDevelopment() {
		return development;
	}

	public long getMarketing() {
		return marketing;
	}

	public long getLostOrders() {
		return lostOrders;
	}

	public long getRevenue() {
		return getPrice() * getUnitsSold();
	}

	public long getGrossProfit() {
		return getRevenue() - getCogs();
	}

	public long getOperatingProfit() {
		return getGrossProfit() - getDevelopment() - getMarketing();
	}

	public void setCompetitiveList(List<ProductSummaryElement> list) {
		competitiveList = list;
	}

	public List<ProductSummaryElement> getCompetitiveList() {
		return competitiveList;
	}

	abstract public long getPlan();

}
