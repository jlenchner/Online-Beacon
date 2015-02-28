package com.insightech.onlinebeacon.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class ClassObject implements Serializable {

	private static final long serialVersionUID = 1L;

	public ClassObject() {
	}

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.SEQUENCE)
	private Long classNumber;

	private String className = "";

	private String classLocation = "";

	private Date creationDate;

	private Date lastUpdate;

	private Date classDate;

	private Date simulationStartDate;

	@Persistent
	private List<Integer> teamCountList = Arrays.asList(new Integer(2));

	public Long getClassNumber() {
		return classNumber;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassLocation() {
		return classLocation;
	}

	public void setClassLocation(String classLocation) {
		this.classLocation = classLocation;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date date) {
		if (creationDate == null)
			creationDate = date;
		lastUpdate = date;
	}

	public Date getClassDate() {
		return classDate;
	}

	public void setClassDate(Date date) {
		classDate = date;
	}

	public int getGames() {
		return teamCountList.size();
	}

	public void setGames(int games) {
		if (games != teamCountList.size()) {
			teamCountList = new ArrayList<Integer>();
			for (int gameIndex = 0; gameIndex < games; gameIndex++) {
				teamCountList.add(new Integer(2));
			}
		}
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public String getDescriptor() {
		if (getClassNumber() != null)
			return getClassNumber().toString() + ": " + getClassName();
		else
			return getClassName();
	}

	public List<Integer> getTeamCountList() {
		return teamCountList;
	}

	public void replaceTeamCountList(List<Integer> newTeamCountList) {
		teamCountList = newTeamCountList;
	}

	public void update(ClassObject co) {
		className = co.getClassName();
		classLocation = co.getClassLocation();
		lastUpdate = co.getLastUpdate();
		classDate = co.getClassDate();
		teamCountList = co.getTeamCountList();
	}

	public Number getNumberOfTeams() {
		int teams = 0;
		for (Integer game : teamCountList) {
			teams += game;
		}
		return teams;
	}

	public Date getSimulationStartDate() {
		return simulationStartDate;
	}

	public void setSimulationStartDate(Date startDate) {
		this.simulationStartDate = startDate;
	}

	public boolean hasSimulationsStarted() {
		return simulationStartDate != null;
	}

	@Override
	public String toString() {
		return "" + classNumber + ", " + className;
	}

}
