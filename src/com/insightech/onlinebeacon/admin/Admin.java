package com.insightech.onlinebeacon.admin;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.insightech.onlinebeacon.admin.commands.UpdateClassObjectList;
import com.insightech.onlinebeacon.admin.panels.AdminTopPanel;

public class Admin implements EntryPoint {

	@Override
	public void onModuleLoad() {
		// AdminDataModel.ADMIN_SERVICE.ping(new AsyncAdapter<Integer>() {
		//
		// @Override
		// public void onSuccess(Integer result) {
		// System.out.println("round Trip." + result);
		// }
		// });

		Scheduler.get().scheduleDeferred(new UpdateClassObjectList());

		RootLayoutPanel.get().add(new AdminTopPanel());

	}
}
