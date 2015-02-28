package com.insightech.onlinebeacon.admin.commands;

import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.insightech.onlinebeacon.admin.AdminDataModel;
import com.insightech.onlinebeacon.admin.popups.EditClassObject;
import com.insightech.onlinebeacon.admin.util.AsyncAdapter;
import com.insightech.onlinebeacon.shared.ClassObject;

public class EditClassFromServer implements ScheduledCommand {

	private Long classNumber;

	public EditClassFromServer(Long classNumber) {
		this.classNumber = classNumber;
	}

	@Override
	public void execute() {
		AdminDataModel.ADMIN_SERVICE.getClassObject(classNumber,
				new AsyncAdapter<ClassObject>() {

					@Override
					public void onSuccess(ClassObject classObject) {
						new EditClassObject(classObject);
					}
				});
	}

}
