package com.insightech.onlinebeacon.admin.commands;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Command;
import com.insightech.onlinebeacon.admin.AdminDataModel;
import com.insightech.onlinebeacon.admin.event.events.ResultsCollectionUpdateEvent;
import com.insightech.onlinebeacon.admin.event.handlers.ResultsCollectionUpdateHandler;
import com.insightech.onlinebeacon.admin.panels.AdminContentTabPanel;
import com.insightech.onlinebeacon.admin.reports.GameSummaryReport;
import com.insightech.onlinebeacon.shared.ResultsCollection;

public class SummarizeGameCommand implements Command {

	ResultsCollectionUpdateHandler firstTimeHandler = new ResultsCollectionUpdateHandler() {

		@Override
		public void onUpdate() {
			oneTimeRegistration.removeHandler();
			repeatRegistration = AdminDataModel.EVENT_BUS.addHandler(
					ResultsCollectionUpdateEvent.TYPE, repeatHandler);
			Scheduler.get().scheduleDeferred(thisCommand);
		}
	};

	ResultsCollectionUpdateHandler repeatHandler = new ResultsCollectionUpdateHandler() {

		@Override
		public void onUpdate() {
			selectSummaryTab();
			report.refresh();
		}
	};

	private static Integer tabIndex = null;
	private static GameSummaryReport report = null;
	private Long classNumber;
	private Long gameNumber;

	private HandlerRegistration oneTimeRegistration;
	private HandlerRegistration repeatRegistration;

	private SummarizeGameCommand thisCommand;

	public SummarizeGameCommand(Long cn, Long gn) {
		classNumber = cn;
		gameNumber = gn;
		thisCommand = this;
	}

	@Override
	public void execute() {
		ResultsCollection collection = AdminDataModel.getRestulsCollection();
		if (collection == null) {
			refreshResultsCollection();
		} else {
			if (collection.getClassNumber() == classNumber
					&& collection.getGameNumber() == gameNumber) {
				System.out.println("got current collection");
				report = new GameSummaryReport();
				if (tabIndex == null) {
					AdminContentTabPanel.addTab(report, "Team Summary");
					tabIndex = new Integer(AdminContentTabPanel.get()
							.getWidgetIndex(report));
					selectSummaryTab();
				} else {
					// TODO update existing report.
				}
			} else {
				refreshResultsCollection();
			}
		}

	}

	public void selectSummaryTab() {
		AdminContentTabPanel.get().selectTab(tabIndex);
	}

	public void refreshResultsCollection() {
		oneTimeRegistration = AdminDataModel.EVENT_BUS.addHandler(
				ResultsCollectionUpdateEvent.TYPE, firstTimeHandler);
		Scheduler.get().scheduleDeferred(
				new GetResultsCommand(classNumber, gameNumber));
	}

}
