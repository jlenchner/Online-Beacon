package com.insightech.onlinebeacon.admin.commands;

import com.google.gwt.user.client.Command;
import com.insightech.onlinebeacon.admin.popups.EditClassObject;
import com.insightech.onlinebeacon.shared.ClassObject;

public class NewClassCommand implements Command {

	@Override
	public void execute() {
		new EditClassObject(new ClassObject());
	}

}
