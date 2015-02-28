package com.insightech.onlinebeacon.admin.menus;

import com.google.gwt.user.client.ui.MenuBar;
import com.insightech.onlinebeacon.admin.commands.NewClassCommand;

public class AdminMenuBar extends MenuBar {

	private static final String id = "AdminMenuBar";

	static public void setAdminMenuStyleName(MenuBar menu) {
		menu.setStyleName(id);
	}

	public AdminMenuBar() {
		setStyleName(id);
		getElement().setId(id);
		addItem("New Class", new NewClassCommand());
		// addItem("List Classes", new ListClassesCommand());
		// addItem("Start Simulations", new StartClassCommand());
	}

}
