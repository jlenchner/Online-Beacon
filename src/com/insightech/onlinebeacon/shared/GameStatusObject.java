package com.insightech.onlinebeacon.shared;

import java.io.Serializable;

public class GameStatusObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private int teamCount;

	private Long classNumber;

	private Long gameNumber;

	private String gameName;

	private int currentYearIndex;

	@SuppressWarnings("unused")
	private GameStatusObject() {
	}

	public GameStatusObject(Long cn, Long gn, String gname, int tc, int yi) {
		classNumber = cn;
		gameNumber = gn;
		gameName = gname;
		teamCount = tc;
		currentYearIndex = yi;
	}

	public int getTeamCount() {
		return teamCount;
	}

	public Long getClassNumber() {
		return classNumber;
	}

	public Long getGameNumber() {
		return gameNumber;
	}

	public String getGameName() {
		return gameName;
	}

	public int getCurrentYearIndex() {
		return currentYearIndex;
	}

}
