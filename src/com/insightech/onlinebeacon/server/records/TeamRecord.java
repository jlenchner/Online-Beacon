package com.insightech.onlinebeacon.server.records;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Serialized;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.insightech.onlinebeacon.admin.reports.TeamSummaryObject;
import com.insightech.onlinebeacon.shared.DecisionObject;
import com.insightech.onlinebeacon.shared.ResultsObject;

@PersistenceCapable
public class TeamRecord implements Serializable {

	@SuppressWarnings("unused")
	private TeamRecord() {
	}

	public TeamRecord(GameRecord gameObject, int team, Key gameKey) {
		classNumber = gameObject.getClassNumber();
		gameNumber = gameObject.getGameKey().getId();
		teamKey = KeyFactory.createKey(gameObject.getGameKey(),
				TeamRecord.class.getSimpleName(), team);
	}

	private Long gameNumber;
	private Long classNumber;

	@Serialized
	private List<ResultsObject> resultsList = new ArrayList<ResultsObject>();

	@Serialized
	private List<DecisionObject> decisionList = new ArrayList<DecisionObject>();

	@PrimaryKey
	private Key teamKey;

	private static final long serialVersionUID = 1L;

	public Long getGameNumber() {
		return gameNumber;
	}

	public Long getClassNumber() {
		return classNumber;
	}

	public Long getTeamNumber() {
		return teamKey.getId();
	}

	public Key getTeamKey() {
		return teamKey;
	}

	public void addResultsObject(ResultsObject resultsObject) {
		resultsList.add(resultsObject);
	}

	public void addDecisionObject(DecisionObject decisionObject) {
		decisionList.add(decisionObject);
	}

	public TeamSummaryObject getTeamSummaryObject() {
		return new TeamSummaryObject(getClassNumber(), getGameNumber(),
				getTeamNumber());
	}

}
