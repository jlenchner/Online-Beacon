package com.insightech.onlinebeacon.shared;

public class TeamSummaryObject {

	private int yearIndex;
	private int teamNumber;
	private Long classNumber;
	private Long gameNumber;
	private long assets;
	private long cash;
	private long inventory;
	private long revenue;
	private long netProfit;
	private long stockPrice;

	public TeamSummaryObject(Long cn, Long gn, int yi, int team,
			ResultsObject results) {
		classNumber = cn;
		gameNumber = gn;
		yearIndex = yi;
		teamNumber = team + 1;
	}

	public int getYearIndex() {
		return yearIndex;
	}

	public int getTeamNumber() {
		return teamNumber;
	}

	public Long getClassNumber() {
		return classNumber;
	}

	public Long getGameNumber() {
		return gameNumber;
	}

	public void setValues(long a, long c, long i, long r, long n, long sp) {
		assets = a;
		cash = c;
		inventory = i;
		revenue = r;
		netProfit = n;
		stockPrice = sp;
	}

	public long getAssets() {
		return assets;
	}

	public long getCash() {
		return cash;
	}

	public long getInventory() {
		return inventory;
	}

	public long getRevenue() {
		return revenue;
	}

	public long getNetProfit() {
		return netProfit;
	}

	public long getStockPrice() {
		return stockPrice;
	}

}
