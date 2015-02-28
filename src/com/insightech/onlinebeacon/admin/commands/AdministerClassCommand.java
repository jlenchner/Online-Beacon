package com.insightech.onlinebeacon.admin.commands;

import java.util.List;

import com.google.gwt.user.client.Command;
import com.insightech.onlinebeacon.admin.AdminDataModel;
import com.insightech.onlinebeacon.admin.util.AsyncAdapter;
import com.insightech.onlinebeacon.shared.GameStatusObject;

public class AdministerClassCommand implements Command {

	@Override
	public void execute() {
		AdminDataModel.ADMIN_SERVICE.getGameSummaryObjectList(AdminDataModel
				.getCurrentClassObject().getClassNumber(),
				new AsyncAdapter<List<GameStatusObject>>() {

					@Override
					public void onSuccess(List<GameStatusObject> list) {
						AdminDataModel.setGameSummaryList(list);
					}
				});

	}

}
