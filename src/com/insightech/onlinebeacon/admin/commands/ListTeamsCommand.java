package com.insightech.onlinebeacon.admin.commands;

import com.google.gwt.user.client.Command;
import com.insightech.onlinebeacon.admin.AdminDataModel;
import com.insightech.onlinebeacon.admin.panels.AdminContentTabPanel;
import com.insightech.onlinebeacon.admin.reports.TeamListReport;

public class ListTeamsCommand implements Command {

	private static Integer tabIndex = null;
	private static TeamListReport report = null;

	@Override
	public void execute() {
		report = new TeamListReport(AdminDataModel.getTeamSummaryObjectList());
		if (tabIndex == null) {
			AdminContentTabPanel.addTab(report, " Team List ");
			tabIndex = new Integer(AdminContentTabPanel.get().getWidgetIndex(
					report));
			AdminContentTabPanel.get().selectTab(tabIndex);
		} else {
			System.out.println("update me");
			// TODO report when tab exists.
		}
	}

}
