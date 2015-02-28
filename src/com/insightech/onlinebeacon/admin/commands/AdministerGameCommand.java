package com.insightech.onlinebeacon.admin.commands;

import java.util.List;

import com.google.gwt.user.client.Command;
import com.insightech.onlinebeacon.admin.AdminDataModel;
import com.insightech.onlinebeacon.admin.reports.TeamSummaryObject;
import com.insightech.onlinebeacon.admin.util.AsyncAdapter;

public class AdministerGameCommand implements Command {

	private Long gameNumber;
	private Long classNumber;

	public AdministerGameCommand(Long cn, Long gn) {
		classNumber = cn;
		gameNumber = gn;
	}

	@Override
	public void execute() {
		AdminDataModel.ADMIN_SERVICE.getTeamSummaryObjectList(classNumber,
				gameNumber, new AsyncAdapter<List<TeamSummaryObject>>() {

					@Override
					public void onSuccess(List<TeamSummaryObject> list) {
						AdminDataModel.setTeamSummaryList(list);
					}
				});
	}

}
