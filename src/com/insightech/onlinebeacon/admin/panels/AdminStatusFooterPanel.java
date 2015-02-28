package com.insightech.onlinebeacon.admin.panels;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.SimplePanel;
import com.insightech.onlinebeacon.admin.AdminDataModel;
import com.insightech.onlinebeacon.admin.event.events.CurrentClassObjectUpdateEvent;
import com.insightech.onlinebeacon.admin.event.handlers.ClassObjectUpdateHandler;
import com.insightech.onlinebeacon.shared.ClassObject;

public class AdminStatusFooterPanel extends SimplePanel implements
		ClassObjectUpdateHandler {

	private final String footerStyleName = "AdminStatusFooter";
	private final FlexTable table = new FlexTable();
	private final String contentStyleName = "AdminStatusFooterContent";

	public AdminStatusFooterPanel() {
		setStyleName(footerStyleName);
		table.setStyleName(contentStyleName);
		add(table);

		AdminDataModel.EVENT_BUS.addHandler(CurrentClassObjectUpdateEvent.TYPE,
				this);
	}

	@Override
	public void onUpdate(ClassObject classObject) {

		table.setText(0, 0, "Current Class: ");
		table.setText(0, 1, classObject.getDescriptor());
	}

}
