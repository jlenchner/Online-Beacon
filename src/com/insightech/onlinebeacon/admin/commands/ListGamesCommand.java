package com.insightech.onlinebeacon.admin.commands;

import com.google.gwt.user.client.Command;
import com.insightech.onlinebeacon.admin.AdminDataModel;
import com.insightech.onlinebeacon.admin.panels.AdminContentTabPanel;
import com.insightech.onlinebeacon.admin.reports.GamesStatusReport;

public class ListGamesCommand implements Command {

	private static Integer tabIndex = null;
	private static GamesStatusReport report = null;

	@Override
	public void execute() {
		report = new GamesStatusReport(AdminDataModel.getGameSummaryList());
		if (tabIndex == null) {
			AdminContentTabPanel.addTab(report, " Game List ");
			tabIndex = new Integer(AdminContentTabPanel.get().getWidgetIndex(
					report));
			AdminContentTabPanel.get().selectTab(tabIndex);
		} else {
			report.refreshReport(AdminDataModel.getGameSummaryList());
		}

	}

}
