package com.insightech.onlinebeacon.admin.panels;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HeaderPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.insightech.onlinebeacon.shared.Version;

public class AdminTopPanel extends HeaderPanel {

	private final String id = "AdminTopPanel";
	private final SimplePanel headerPanel = new SimplePanel();
	private final HTML headerHTML = new HTML("Admin Dashboard");
	private final String headerId = "AdminHeader";

	private final SimplePanel footerPanel = new SimplePanel();
	private final HTML footerHtml = new HTML(Version.getVersion());
	private final String footerId = "AdminFooter";

	private final AdminStatusPanel contentPanel = new AdminStatusPanel();

	public AdminTopPanel() {
		super();
		getElement().setId(id);
		setStyleName(id);

		headerPanel.setStyleName(headerId);
		headerPanel.add(headerHTML);
		headerPanel.getElement().setId(headerId);
		setHeaderWidget(headerPanel);

		footerPanel.setStyleName(footerId);
		footerPanel.add(footerHtml);
		footerPanel.getElement().setId(footerId);
		setFooterWidget(footerPanel);

		setContentWidget(contentPanel);
	}

}
