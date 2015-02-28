package com.insightech.onlinebeacon.admin.reports;

import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.insightech.onlinebeacon.admin.AdminDataModel;
import com.insightech.onlinebeacon.shared.TeamSummaryObject;

public class GameSummaryReport extends CellTable<TeamSummaryObject> {

	NumberCell classNumber = new NumberCell();
	Column<TeamSummaryObject, Number> classNumberColumn = new Column<TeamSummaryObject, Number>(
			classNumber) {

		@Override
		public Number getValue(TeamSummaryObject tso) {
			return tso.getClassNumber();
		}
	};

	NumberCell gameNumber = new NumberCell();
	Column<TeamSummaryObject, Number> gameNumberColumn = new Column<TeamSummaryObject, Number>(
			gameNumber) {

		@Override
		public Number getValue(TeamSummaryObject tso) {
			return tso.getGameNumber();
		}

	};

	NumberCell teamNumber = new NumberCell();
	Column<TeamSummaryObject, Number> teamNumberColumn = new Column<TeamSummaryObject, Number>(
			teamNumber) {

		@Override
		public Number getValue(TeamSummaryObject tso) {
			return tso.getTeamNumber();
		}

	};

	NumberCell stockPriceCell = new NumberCell();
	Column<TeamSummaryObject, Number> stockPriceColumn = new Column<TeamSummaryObject, Number>(
			stockPriceCell) {

		@Override
		public Number getValue(TeamSummaryObject tso) {
			return tso.getStockPrice();
		}

	};

	NumberCell netProfitCell = new NumberCell();
	Column<TeamSummaryObject, Number> netProfitColumn = new Column<TeamSummaryObject, Number>(
			netProfitCell) {

		@Override
		public Number getValue(TeamSummaryObject tso) {
			return tso.getNetProfit();
		}

	};

	NumberCell cashCell = new NumberCell();
	Column<TeamSummaryObject, Number> cashColumn = new Column<TeamSummaryObject, Number>(
			cashCell) {

		@Override
		public Number getValue(TeamSummaryObject tso) {
			return tso.getCash();
		}

	};

	NumberCell revenueCell = new NumberCell();
	Column<TeamSummaryObject, Number> revenueColumn = new Column<TeamSummaryObject, Number>(
			revenueCell) {

		@Override
		public Number getValue(TeamSummaryObject tso) {
			return tso.getRevenue();
		}

	};

	NumberCell assetsCell = new NumberCell();
	Column<TeamSummaryObject, Number> assetsColumn = new Column<TeamSummaryObject, Number>(
			assetsCell) {

		@Override
		public Number getValue(TeamSummaryObject tso) {
			return tso.getAssets();
		}

	};

	NumberCell inventoryCell = new NumberCell();
	Column<TeamSummaryObject, Number> inventoryColumn = new Column<TeamSummaryObject, Number>(
			inventoryCell) {

		@Override
		public Number getValue(TeamSummaryObject tso) {
			return tso.getInventory();
		}

	};

	public GameSummaryReport() {
		classNumberColumn
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		addColumn(classNumberColumn, "Class");

		gameNumberColumn
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		addColumn(gameNumberColumn, "Game");

		teamNumberColumn
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		addColumn(teamNumberColumn, "Team");

		stockPriceColumn
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		addColumn(stockPriceColumn, "Stock");

		netProfitColumn
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		addColumn(netProfitColumn, "Profit");

		cashColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		addColumn(cashColumn, "Cash");

		revenueColumn
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		addColumn(revenueColumn, "Revenue");

		assetsColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		addColumn(assetsColumn, "Assets");

		inventoryColumn
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		addColumn(inventoryColumn, "Inventory");

		refresh();
	}

	public void refresh() {
		setRowData(AdminDataModel.getRestulsCollection().getTeamSummaryList());
	}

}
