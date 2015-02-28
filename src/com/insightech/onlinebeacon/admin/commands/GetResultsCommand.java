package com.insightech.onlinebeacon.admin.commands;

import com.google.gwt.user.client.Command;
import com.insightech.onlinebeacon.admin.AdminDataModel;
import com.insightech.onlinebeacon.admin.util.AsyncAdapter;
import com.insightech.onlinebeacon.shared.ResultsCollection;

public class GetResultsCommand implements Command {

	private Long classNumber;
	private Long gameNumber;

	public GetResultsCommand(Long cn, Long gn) {
		classNumber = cn;
		gameNumber = gn;
	}

	@Override
	public void execute() {
		AdminDataModel.ADMIN_SERVICE.getResults(classNumber, gameNumber,
				new AsyncAdapter<ResultsCollection>() {

					@Override
					public void onSuccess(ResultsCollection results) {
						AdminDataModel.setResultsCollection(results);
					}
				});
	}

}
