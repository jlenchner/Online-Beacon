package com.insightech.onlinebeacon.server.records;

import java.io.Serializable;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.insightech.beacon.simulator.AbstractIndustry;
import com.insightech.beacon.simulator.AlphaIndustry;
import com.insightech.beacon.simulator.BetaIndustry;
import com.insightech.beacon.simulator.ChiIndustry;
import com.insightech.beacon.simulator.SigmaIndustry;
import com.insightech.beacon.simulator.Simulator;
import com.insightech.onlinebeacon.shared.ClassObject;
import com.insightech.onlinebeacon.shared.Constants;
import com.insightech.onlinebeacon.shared.GameStatusObject;

@PersistenceCapable
public class GameRecord implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long classNumber;
	private String gameName;
	private int teamCount;
	AbstractIndustry[] industryArray = { new AlphaIndustry(),
			new BetaIndustry(), new ChiIndustry(), new SigmaIndustry() };

	@Persistent(serialized = "true")
	Simulator simObject;

	@SuppressWarnings("unused")
	private GameRecord() {
	}

	// TODO put game object in group with class object.
	// TODO streamline game and team objects using keys, id and parent;

	public GameRecord(ClassObject co, int game, int teams, Key coKey) {

		classNumber = co.getClassNumber();
		gameName = "Game_" + game;
		teamCount = teams;
		gameKey = KeyFactory.createKey(coKey, GameRecord.class.getSimpleName(),
				game);
		String[] teamArray = Constants.GET_TEAM_NAME_ARRAY(teams);
		Simulator.newSimulation(teamArray, industryArray);
		simObject = Simulator.getInstance();
		simObject.runSimulation();
	}

	public Long getClassNumber() {
		return classNumber;
	}

	@PrimaryKey
	private Key gameKey;

	public Long getGameNumber() {
		return gameKey.getId();
	}

	public String getGameName() {
		return gameName;
	}

	public int getTeamCount() {
		return teamCount;
	}

	public Simulator getSim() {
		Simulator.setSimulator(simObject);
		return Simulator.getInstance();
	}

	public void setSim(Simulator simulator) {
		simObject = simulator;
	}

	public Key getGameKey() {
		return gameKey;
	}

	public GameStatusObject getSummary() {
		Simulator sim = getSim();
		return new GameStatusObject(classNumber, getGameNumber(), gameName,
				teamCount, sim.getCurrentYearIndex());
	}

}
