package com.insightech.onlinebeacon.admin.panels;

import com.google.gwt.user.client.ui.HeaderPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.insightech.onlinebeacon.admin.menus.AdminMenuBar;

public class AdminActionPanel extends HeaderPanel {

	private final static String id = "AdminActionPanel";
	private final AdminMenuBar menuBar = new AdminMenuBar();
	private final static AdminContentTabPanel contentPanel = new AdminContentTabPanel();
	private final SimplePanel menuBarContainer = new SimplePanel();

	public AdminActionPanel() {
		getElement().setId(id);
		setStyleName(id);

		setHeaderWidget(menuBarContainer);
		menuBarContainer.setStyleName("menuBarContainer");
		// menuBarContainer.add(menuBar);

		setContentWidget(contentPanel);
	}

}
