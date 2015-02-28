package com.insightech.onlinebeacon.admin.commands;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.insightech.onlinebeacon.admin.AdminDataModel;
import com.insightech.onlinebeacon.admin.panels.AdminContentTabPanel;
import com.insightech.onlinebeacon.admin.reports.ClassObjectListReport;

public class ListClassesCommand implements Command {

	private static Integer tabIndex = null;
	private static ClassObjectListReport report = null;

	@Override
	public void execute() {
		if (AdminDataModel.getClassObjectList() == null)
			Window.alert("Class summary list is empty.");
		else {
			report = new ClassObjectListReport(
					AdminDataModel.getClassObjectList());
			if (tabIndex == null) {
				AdminContentTabPanel.addTab(report, " Class List ");
				tabIndex = new Integer(AdminContentTabPanel.get()
						.getWidgetIndex(report));
			}
		}
	}
}
