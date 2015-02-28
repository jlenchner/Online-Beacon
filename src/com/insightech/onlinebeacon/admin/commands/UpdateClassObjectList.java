package com.insightech.onlinebeacon.admin.commands;

import java.util.List;

import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.insightech.onlinebeacon.admin.AdminDataModel;
import com.insightech.onlinebeacon.admin.util.AsyncAdapter;
import com.insightech.onlinebeacon.shared.ClassObject;

public class UpdateClassObjectList implements ScheduledCommand {

	@Override
	public void execute() {
		AdminDataModel.ADMIN_SERVICE
				.getClassObjectList(new AsyncAdapter<List<ClassObject>>() {

					@Override
					public void onSuccess(List<ClassObject> result) {
						AdminDataModel.setClassObjectList(result);
					}
				});
	}
}
