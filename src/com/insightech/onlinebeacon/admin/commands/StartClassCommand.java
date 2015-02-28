package com.insightech.onlinebeacon.admin.commands;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.insightech.onlinebeacon.admin.AdminDataModel;
import com.insightech.onlinebeacon.admin.util.AsyncAdapter;
import com.insightech.onlinebeacon.shared.ClassObject;

public class StartClassCommand implements Command {

	private ClassObject classObject;

	public StartClassCommand(ClassObject co) {
		classObject = co;
	}

	@Override
	public void execute() {
		if (classObject.hasSimulationsStarted())
			if (Window
					.confirm("Simulations have already started. Do you want to restart?"))
				startSimulations(classObject);
			else {
				Window.alert("Simulation NOT started.");
			}
		else {
			startSimulations(classObject);
		}
	}

	public void startSimulations(final ClassObject currentClassObject) {
		AdminDataModel.ADMIN_SERVICE.startClass(
				currentClassObject.getClassNumber(), new AsyncAdapter<Void>() {

					@Override
					public void onSuccess(Void result) {
						Window.alert("Class " + currentClassObject
								+ " started.");
					}
				});
	}

}
