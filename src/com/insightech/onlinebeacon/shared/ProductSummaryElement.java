package com.insightech.onlinebeacon.shared;

import java.io.Serializable;

public class ProductSummaryElement implements Serializable {
	private static final long serialVersionUID = 1L;

	protected ProductSummaryElement() {
	}

	private String companyName;
	private long price;
	private long unitsSold;
	private long inventoryDollars;
	private long grossProfit;
	private long operatingProfit;
	private long capacity;

	public ProductSummaryElement(String name, long p, long s, long i, long g,
			long o, long c) {
		this(name, p, s, g, o);
		inventoryDollars = i;
		capacity = c;
	}

	public ProductSummaryElement(String name, long p, long s, long g, long o) {
		companyName = name;
		price = p;
		unitsSold = s;
		operatingProfit = o;
		grossProfit = g;
	}

	public String getCompanyName() {
		return companyName;
	}

	public long getPrice() {
		return price;
	}

	public long getUnitsSold() {
		return unitsSold;
	}

	public long getInventoryDollars() {
		return inventoryDollars;
	}

	public long getGrossProfit() {
		return grossProfit;
	}

	public long getOperatingProfit() {
		return operatingProfit;
	}

	public long getCapacity() {
		return capacity;
	}

	public long getRevenue() {
		return getPrice() * getUnitsSold();

	}

}
