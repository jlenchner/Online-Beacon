package com.insightech.onlinebeacon.admin;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Window;
import com.insightech.onlinebeacon.admin.event.events.ClassObjectListUpdateEvent;
import com.insightech.onlinebeacon.admin.event.events.CurrentClassObjectUpdateEvent;
import com.insightech.onlinebeacon.admin.event.events.GameStatusListUpdateEvent;
import com.insightech.onlinebeacon.admin.event.events.ResultsCollectionUpdateEvent;
import com.insightech.onlinebeacon.admin.event.events.TeamSummaryListUpdateEvent;
import com.insightech.onlinebeacon.admin.reports.TeamSummaryObject;
import com.insightech.onlinebeacon.admin.util.AsyncAdapter;
import com.insightech.onlinebeacon.shared.ClassObject;
import com.insightech.onlinebeacon.shared.GameStatusObject;
import com.insightech.onlinebeacon.shared.ResultsCollection;

public class AdminDataModel {

	public static final EventBus EVENT_BUS = new SimpleEventBus();
	public static final AdminServiceAsync ADMIN_SERVICE = GWT
			.create(AdminService.class);
	private static ClassObject currentClassObject = null;
	private static List<ClassObject> classObjectList;
	private static List<GameStatusObject> gameSummaryList = null;
	private static List<TeamSummaryObject> teamSummaryList;
	private static ResultsCollection resultsCollection;

	// TODO add event.
	public static void saveClassObject(final ClassObject classObject) {
		ADMIN_SERVICE.saveClassObject(classObject,
				new AsyncAdapter<List<ClassObject>>() {

					@Override
					public void onSuccess(List<ClassObject> list) {
						setClassObjectList(list);
						Window.alert("Class " + classObject.getDescriptor()
								+ " saved.");
					}

				});
	}

	public static ClassObject getCurrentClassObject() {
		return currentClassObject;
	}

	public static void setCurrentClassObject(ClassObject currentClassObject) {
		AdminDataModel.currentClassObject = currentClassObject;
		EVENT_BUS.fireEvent(new CurrentClassObjectUpdateEvent());
	}

	public static void setClassObjectList(List<ClassObject> list) {
		classObjectList = list;
		EVENT_BUS.fireEvent(new ClassObjectListUpdateEvent());
	}

	public static List<ClassObject> getClassObjectList() {
		return classObjectList;
	}

	public static void setGameSummaryList(List<GameStatusObject> list) {
		gameSummaryList = list;
		EVENT_BUS.fireEvent(new GameStatusListUpdateEvent());
	}

	public static List<GameStatusObject> getGameSummaryList() {
		return gameSummaryList;
	}

	public static void setTeamSummaryList(List<TeamSummaryObject> list) {
		teamSummaryList = list;
		EVENT_BUS.fireEvent(new TeamSummaryListUpdateEvent());
	}

	public static List<TeamSummaryObject> getTeamSummaryObjectList() {
		return teamSummaryList;
	}

	public static void setResultsCollection(ResultsCollection results) {
		resultsCollection = results;
		EVENT_BUS.fireEvent(new ResultsCollectionUpdateEvent());
	}

	public static ResultsCollection getRestulsCollection() {
		return resultsCollection;
	}

}
