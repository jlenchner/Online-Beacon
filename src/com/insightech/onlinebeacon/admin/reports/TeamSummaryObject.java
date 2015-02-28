package com.insightech.onlinebeacon.admin.reports;

import java.io.Serializable;

public class TeamSummaryObject implements Serializable {

	@SuppressWarnings("unused")
	private TeamSummaryObject() {
	}

	private Long classNumber;
	private Long gameNumber;
	private Long teamNumber;

	public TeamSummaryObject(Long cn, Long gn, Long tn) {
		classNumber = cn;
		gameNumber = gn;
		teamNumber = tn;
	}

	private static final long serialVersionUID = 1L;

	public Long getClassNumber() {
		return classNumber;
	}

	public Long getGameNumber() {
		return gameNumber;
	}

	public Long getTeamNumber() {
		return teamNumber;
	}

}
