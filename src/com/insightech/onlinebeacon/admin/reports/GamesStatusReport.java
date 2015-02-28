package com.insightech.onlinebeacon.admin.reports;

import java.util.List;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.insightech.onlinebeacon.admin.commands.AdministerGameCommand;
import com.insightech.onlinebeacon.admin.commands.SummarizeGameCommand;
import com.insightech.onlinebeacon.admin.util.Common;
import com.insightech.onlinebeacon.shared.GameStatusObject;

public class GamesStatusReport extends CellTable<GameStatusObject> {

	NumberCell classNumber = new NumberCell();
	Column<GameStatusObject, Number> classNumberColumn = new Column<GameStatusObject, Number>(
			classNumber) {

		@Override
		public Number getValue(GameStatusObject gso) {
			return gso.getClassNumber();
		}
	};

	NumberCell gameNumber = new NumberCell();
	Column<GameStatusObject, Number> gameNumberColumn = new Column<GameStatusObject, Number>(
			gameNumber) {

		@Override
		public Number getValue(GameStatusObject gso) {
			return gso.getGameNumber();
		}
	};

	NumberCell teamCount = new NumberCell();
	Column<GameStatusObject, Number> teamCountColumn = new Column<GameStatusObject, Number>(
			teamCount) {

		@Override
		public Number getValue(GameStatusObject gso) {
			return gso.getTeamCount();
		}
	};

	TextColumn<GameStatusObject> yearColumn = new TextColumn<GameStatusObject>() {

		@Override
		public String getValue(GameStatusObject gso) {
			return Common.getYear(gso.getCurrentYearIndex()).toString();
		}
	};

	ButtonCell adminButton = new ButtonCell();
	Column<GameStatusObject, String> adminColumn = new Column<GameStatusObject, String>(
			adminButton) {

		@Override
		public String getValue(GameStatusObject object) {
			return "Select";
		}
	};

	ButtonCell statusButton = new ButtonCell();
	Column<GameStatusObject, String> statusColumn = new Column<GameStatusObject, String>(
			statusButton) {

		@Override
		public String getValue(GameStatusObject object) {
			return "Select";
		}

	};

	ButtonCell summaryButton = new ButtonCell();
	Column<GameStatusObject, String> summaryColumn = new Column<GameStatusObject, String>(
			summaryButton) {

		@Override
		public String getValue(GameStatusObject object) {
			return "Select";
		}

	};

	public GamesStatusReport(List<GameStatusObject> list) {
		classNumberColumn
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		addColumn(classNumberColumn, "Class Number");

		gameNumberColumn
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		addColumn(gameNumberColumn, "Game Number");

		teamCountColumn
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		addColumn(teamCountColumn, "Team Count");

		yearColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		addColumn(yearColumn, "Year");

		adminColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		addColumn(adminColumn, "Administer");
		adminColumn
				.setFieldUpdater(new FieldUpdater<GameStatusObject, String>() {

					@Override
					public void update(int index, GameStatusObject gso,
							String value) {
						Scheduler.get().scheduleEntry(
								new AdministerGameCommand(gso.getClassNumber(),
										gso.getGameNumber()));
					}
				});

		statusColumn
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		addColumn(statusColumn, "Status");
		statusColumn
				.setFieldUpdater(new FieldUpdater<GameStatusObject, String>() {

					@Override
					public void update(int index, GameStatusObject gso,
							String value) {
						System.out.println("get game status");
					}
				});

		summaryColumn
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		addColumn(summaryColumn, "Summary");
		summaryColumn
				.setFieldUpdater(new FieldUpdater<GameStatusObject, String>() {

					@Override
					public void update(int index, GameStatusObject gso,
							String value) {
						Scheduler.get().scheduleDeferred(
								new SummarizeGameCommand(gso.getClassNumber(),
										gso.getGameNumber()));
					}
				});

		refreshReport(list);

	}

	public void refreshReport(List<GameStatusObject> list) {
		setRowData(list);
	}

}
