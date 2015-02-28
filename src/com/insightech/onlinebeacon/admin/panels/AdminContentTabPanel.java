package com.insightech.onlinebeacon.admin.panels;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.insightech.onlinebeacon.admin.AdminDataModel;
import com.insightech.onlinebeacon.admin.commands.AdministerClassCommand;
import com.insightech.onlinebeacon.admin.commands.ListClassesCommand;
import com.insightech.onlinebeacon.admin.commands.ListGamesCommand;
import com.insightech.onlinebeacon.admin.commands.ListResultsCommand;
import com.insightech.onlinebeacon.admin.commands.ListTeamsCommand;
import com.insightech.onlinebeacon.admin.event.events.ClassObjectListUpdateEvent;
import com.insightech.onlinebeacon.admin.event.events.CurrentClassObjectUpdateEvent;
import com.insightech.onlinebeacon.admin.event.events.GameStatusListUpdateEvent;
import com.insightech.onlinebeacon.admin.event.events.ResultsCollectionUpdateEvent;
import com.insightech.onlinebeacon.admin.event.events.TeamSummaryListUpdateEvent;
import com.insightech.onlinebeacon.admin.event.handlers.ClassObjectListUpdateHandler;
import com.insightech.onlinebeacon.admin.event.handlers.ClassObjectUpdateHandler;
import com.insightech.onlinebeacon.admin.event.handlers.GameStatusListUpdateHandler;
import com.insightech.onlinebeacon.admin.event.handlers.ResultsCollectionUpdateHandler;
import com.insightech.onlinebeacon.admin.event.handlers.TeamSummaryListUpdatteHandler;
import com.insightech.onlinebeacon.admin.reports.GamesStatusReport;
import com.insightech.onlinebeacon.shared.ClassObject;

public class AdminContentTabPanel extends TabLayoutPanel {

	private static Integer gameListTabIndex = null;
	private static GamesStatusReport gameListReport = null;

	ClassObjectListUpdateHandler listHandler = new ClassObjectListUpdateHandler() {

		@Override
		public void onUpdate() {
			new ListClassesCommand().execute();
			// TODO why am I removing the handler?
			// listRegistration.removeHandler();
		}
	};

	ClassObjectUpdateHandler currentClassHandler = new ClassObjectUpdateHandler() {

		@Override
		public void onUpdate(ClassObject classObject) {
			Scheduler.get().scheduleDeferred(new AdministerClassCommand());
		}
	};

	GameStatusListUpdateHandler gameListHandler = new GameStatusListUpdateHandler() {

		@Override
		public void onUpdate() {
			Scheduler.get().scheduleDeferred(new ListGamesCommand());
		}
	};

	TeamSummaryListUpdatteHandler teamListHandle = new TeamSummaryListUpdatteHandler() {

		@Override
		public void onUpdate() {
			Scheduler.get().scheduleDeferred(new ListTeamsCommand());
		}
	};

	ResultsCollectionUpdateHandler resultsHandler = new ResultsCollectionUpdateHandler() {

		@Override
		public void onUpdate() {
			Scheduler.get().scheduleDeferred(new ListResultsCommand());
			// TODO list summary command.
		}
	};

	static AdminContentTabPanel panel = null;
	private HandlerRegistration listRegistration;
	private HandlerRegistration classObjectRegistration;
	private HandlerRegistration gameListRegistration;
	private HandlerRegistration teamListRegistration;
	private HandlerRegistration resultsRegistration;

	public AdminContentTabPanel() {
		super(1.5, Unit.EM);
		panel = this;
		setStyleName("AdminContentTabPanel");

		listRegistration = AdminDataModel.EVENT_BUS.addHandler(
				ClassObjectListUpdateEvent.TYPE, listHandler);

		classObjectRegistration = AdminDataModel.EVENT_BUS.addHandler(
				CurrentClassObjectUpdateEvent.TYPE, currentClassHandler);

		gameListRegistration = AdminDataModel.EVENT_BUS.addHandler(
				GameStatusListUpdateEvent.TYPE, gameListHandler);

		teamListRegistration = AdminDataModel.EVENT_BUS.addHandler(
				TeamSummaryListUpdateEvent.TYPE, teamListHandle);

		resultsRegistration = AdminDataModel.EVENT_BUS.addHandler(
				ResultsCollectionUpdateEvent.TYPE, resultsHandler);

	}

	public static void addTab(Widget widget, String tabTitle) {
		if (panel != null) {
			panel.add(widget, tabTitle);
		}
	}

	public static AdminContentTabPanel get() {
		return panel;
	}

}
