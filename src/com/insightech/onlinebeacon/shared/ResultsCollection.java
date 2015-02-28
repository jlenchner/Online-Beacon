package com.insightech.onlinebeacon.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.insightech.onlinebeacon.admin.AdminDataModel;

public class ResultsCollection implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long classNumber;
	private Long gameNumber;

	private List<ResultsObject> resultsList = new ArrayList<ResultsObject>();
	private int teamCount;

	@SuppressWarnings("unused")
	private ResultsCollection() {
	}

	public ResultsCollection(Long cn, Long gn, int teams) {
		classNumber = cn;
		gameNumber = gn;
		teamCount = teams;
	}

	public Long getClassNumber() {
		return classNumber;
	}

	public Long getGameNumber() {
		return gameNumber;
	}

	public void add(ResultsObject resultsObject) {
		resultsList.add(resultsObject);
	}

	public ResultsObject getResults(int yearIndex, int teamIndex) {
		int index = (yearIndex * teamCount) + teamIndex;
		return resultsList.get(index);
	}

	public List<TeamSummaryObject> getTeamSummaryList() {
		List<TeamSummaryObject> list = new ArrayList<TeamSummaryObject>();
		int yearIndex = (resultsList.size() / teamCount) - 1;
		for (int team = 0; team < teamCount; team++) {
			TeamSummaryObject summary = new TeamSummaryObject(classNumber,
					gameNumber, yearIndex, team, AdminDataModel
							.getRestulsCollection().getResults(yearIndex, team));
			ResultsObject results = getResults(yearIndex, team);
			summary.setValues(results.getAssets(), results.getCash(),
					results.getInventory(), results.getRevenue(),
					results.getNetProfit(), results.getStockPrice());
			list.add(summary);
		}
		return list;
	}

}
