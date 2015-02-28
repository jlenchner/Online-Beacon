package com.insightech.onlinebeacon.shared;

import java.io.Serializable;
import java.util.Date;

public class ClassSummaryObject implements Serializable {

	@SuppressWarnings("unused")
	private ClassSummaryObject() {
	}

	private static final long serialVersionUID = 1L;
	private Long classNumber;
	private String className;
	private String classLocation;
	private Date classDate;
	private int games;

	public ClassSummaryObject(ClassObject object) {
		setClassNumber(object.getClassNumber());
		className = object.getClassName();
		classLocation = object.getClassLocation();
		classDate = object.getClassDate();
		games = object.getGames();
	}

	public Long getClassNumber() {
		return classNumber;
	}

	private void setClassNumber(Long classNumber) {
		this.classNumber = classNumber;
	}

	public String getClassName() {
		return className;
	}

	public String getClassLocation() {
		return classLocation;
	}

	public Date getClassDate() {
		return classDate;
	}

	public int getGames() {
		return games;
	}

	public String getDescriptor() {
		return "#" + classNumber + " " + className;
	}

	public String getDescripton() {
		return getClassNumber().toString() + ", " + getClassName();
	}

}
