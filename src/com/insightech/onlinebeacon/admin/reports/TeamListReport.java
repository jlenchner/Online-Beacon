package com.insightech.onlinebeacon.admin.reports;

import java.util.List;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.insightech.onlinebeacon.admin.commands.GetResultsCommand;

public class TeamListReport extends CellTable<TeamSummaryObject> {
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

	ButtonCell resultsButton = new ButtonCell();
	Column<TeamSummaryObject, String> resultsColumn = new Column<TeamSummaryObject, String>(
			resultsButton) {

		@Override
		public String getValue(TeamSummaryObject object) {
			return "Select";
		}

	};

	public TeamListReport(List<TeamSummaryObject> list) {
		classNumberColumn
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		addColumn(classNumberColumn, "Class Number");

		gameNumberColumn
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		addColumn(gameNumberColumn, "Game Number");

		teamNumberColumn
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		addColumn(teamNumberColumn, "Team Number");

		resultsColumn
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		addColumn(resultsColumn, "Results");
		resultsColumn
				.setFieldUpdater(new FieldUpdater<TeamSummaryObject, String>() {

					@Override
					public void update(int index, TeamSummaryObject tso,
							String value) {
						Scheduler.get().scheduleDeferred(
								new GetResultsCommand(tso.getClassNumber(), tso
										.getGameNumber()));
					}
				});

		refreshReport(list);
	}

	public void refreshReport(List<TeamSummaryObject> list) {
		setRowData(list);
	}
}
