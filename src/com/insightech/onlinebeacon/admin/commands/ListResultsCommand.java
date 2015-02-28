package com.insightech.onlinebeacon.admin.commands;

import com.google.gwt.user.client.Command;
import com.insightech.onlinebeacon.admin.panels.AdminContentTabPanel;
import com.insightech.onlinebeacon.admin.reports.ResultsListReport;

public class ListResultsCommand implements Command {

	private static Integer tabIndex = null;
	private static ResultsListReport report = null;

	@Override
	public void execute() {
		report = new ResultsListReport();
		if (tabIndex == null) {
			AdminContentTabPanel.addTab(report, " Results List ");
			tabIndex = new Integer(AdminContentTabPanel.get().getWidgetIndex(
					report));
			AdminContentTabPanel.get().selectTab(tabIndex);
		} else {
			// TODO report when tab exists.
		}
	}
}
