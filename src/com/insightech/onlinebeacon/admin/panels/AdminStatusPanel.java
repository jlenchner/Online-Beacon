package com.insightech.onlinebeacon.admin.panels;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HeaderPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class AdminStatusPanel extends HeaderPanel {

	private final String id = "AdminStatusPanel";
	private final SimplePanel headerPanel = new SimplePanel();
	private final String headerId = "AdminStatusHeader";
	private final AdminStatusFooterPanel footerPanel = new AdminStatusFooterPanel();
	private final AdminActionPanel actionPanel = new AdminActionPanel();

	public AdminStatusPanel() {
		getElement().setId(id);
		setStyleName(id);
		headerPanel.setStyleName(headerId);
		headerPanel.add(new HTML("Admin Status Header"));
		setHeaderWidget(headerPanel);
		setFooterWidget(footerPanel);
		setContentWidget(actionPanel);
	}

}
