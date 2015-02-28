package com.insightech.onlinebeacon.admin.reports;

import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.insightech.onlinebeacon.admin.AdminDataModel;
import com.insightech.onlinebeacon.admin.commands.EditClassFromServer;
import com.insightech.onlinebeacon.admin.commands.StartClassCommand;
import com.insightech.onlinebeacon.admin.event.events.ClassObjectListUpdateEvent;
import com.insightech.onlinebeacon.admin.event.handlers.ClassObjectListUpdateHandler;
import com.insightech.onlinebeacon.admin.util.AsyncAdapter;
import com.insightech.onlinebeacon.shared.ClassObject;

public class ClassObjectListReport extends CellTable<ClassObject> {

	NumberCell classNumber = new NumberCell();

	Column<ClassObject, Number> classNumberColumn = new Column<ClassObject, Number>(
			classNumber) {

		@Override
		public Number getValue(ClassObject object) {
			return object.getClassNumber();
		}
	};

	TextColumn<ClassObject> classNameColumn = new TextColumn<ClassObject>() {

		@Override
		public String getValue(ClassObject object) {
			return object.getClassName();
		}
	};

	TextColumn<ClassObject> classLocationColumn = new TextColumn<ClassObject>() {

		@Override
		public String getValue(ClassObject object) {
			return object.getClassLocation();
		}
	};

	DateTimeFormat formatter = DateTimeFormat
			.getFormat(PredefinedFormat.DATE_MEDIUM);

	DateCell classDateCell = new DateCell(formatter);

	Column<ClassObject, Date> classDateColumn = new Column<ClassObject, Date>(
			classDateCell) {

		@Override
		public Date getValue(ClassObject object) {
			return object.getClassDate();
		}
	};

	DateCell simulationStartDateCell = new DateCell(formatter);

	Column<ClassObject, Date> simulationStartDateColumn = new Column<ClassObject, Date>(
			simulationStartDateCell) {

		@Override
		public Date getValue(ClassObject object) {
			return object.getSimulationStartDate();
		}
	};

	NumberCell numberOfGamesCell = new NumberCell();

	Column<ClassObject, Number> gamesColumn = new Column<ClassObject, Number>(
			numberOfGamesCell) {

		@Override
		public Number getValue(ClassObject object) {
			return object.getGames();
		}
	};

	NumberCell numberOfTeamsCell = new NumberCell();

	Column<ClassObject, Number> teamsColumn = new Column<ClassObject, Number>(
			numberOfGamesCell) {

		@Override
		public Number getValue(ClassObject object) {
			return object.getNumberOfTeams();
		}

	};

	ButtonCell editButton = new ButtonCell();
	Column<ClassObject, String> editColumn = new Column<ClassObject, String>(
			editButton) {

		@Override
		public String getValue(ClassObject object) {
			return "Edit";
		}
	};

	ButtonCell adminButton = new ButtonCell();
	Column<ClassObject, String> adminColumn = new Column<ClassObject, String>(
			adminButton) {

		@Override
		public String getValue(ClassObject object) {
			return "Select";
		}
	};

	ButtonCell startButton = new ButtonCell();
	Column<ClassObject, String> startColumn = new Column<ClassObject, String>(
			startButton) {

		@Override
		public String getValue(ClassObject object) {
			return "Start";
		}

	};

	ButtonCell deleteButton = new ButtonCell();
	Column<ClassObject, String> deleteColumn = new Column<ClassObject, String>(
			deleteButton) {

		@Override
		public String getValue(ClassObject object) {
			return "Delete";
		}
	};

	public ClassObjectListReport(List<ClassObject> classObjectList) {
		classNumberColumn
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		addColumn(classNumberColumn, "Class Number");

		addColumn(classNameColumn, "Class Name");

		addColumn(classLocationColumn, "Class Location");

		addColumn(classDateColumn, "Class Date");

		simulationStartDateColumn
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		addColumn(simulationStartDateColumn, "Simulation Start Date");

		gamesColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		addColumn(gamesColumn, "Games");

		teamsColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		addColumn(teamsColumn, "Teams");

		addColumn(editColumn, "Edit");
		editColumn.setFieldUpdater(new FieldUpdater<ClassObject, String>() {

			@Override
			public void update(int index, ClassObject object, String value) {
				Scheduler.get().scheduleDeferred(
						new EditClassFromServer(object.getClassNumber()));
			}
		});

		addColumn(adminColumn, "Administer");
		adminColumn.setFieldUpdater(new FieldUpdater<ClassObject, String>() {

			@Override
			public void update(int index, ClassObject classObject, String value) {
				if (classObject.hasSimulationsStarted()) {
					AdminDataModel.setCurrentClassObject(classObject);
				} else
					Window.alert("Simulation had not started for "
							+ classObject.getDescriptor());
			}
		});

		startColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		addColumn(startColumn, "Start Simulation");
		startColumn.setFieldUpdater(new FieldUpdater<ClassObject, String>() {

			@Override
			public void update(int index, ClassObject classObject, String value) {
				Scheduler.get().scheduleDeferred(
						new StartClassCommand(classObject));
			}
		});

		addColumn(deleteColumn, "Delete");
		deleteColumn.setFieldUpdater(new FieldUpdater<ClassObject, String>() {

			@Override
			public void update(int index, ClassObject classObject, String value) {
				if (classObject.hasSimulationsStarted()) {
					if (Window.confirm("Class "
							+ classObject.getDescriptor()
							+ " has started. Do you want to deleted all games and teams with the class record?"))
						executeDelete(classObject);
				} else {
					if (Window.confirm("Are you sure you want to delete class "
							+ classObject.getDescriptor())) {
						executeDelete(classObject);
					}
				}
			}

			public void executeDelete(ClassObject classObject) {
				AdminDataModel.ADMIN_SERVICE.deleteClassFromDatabase(
						classObject.getClassNumber(),
						new AsyncAdapter<List<ClassObject>>() {

							@Override
							public void onSuccess(List<ClassObject> list) {
								AdminDataModel.setClassObjectList(list);
							}

						});
			}
		});
		generateReport(classObjectList);

		ClassObjectListUpdateHandler handler = new ClassObjectListUpdateHandler() {

			@Override
			public void onUpdate() {
				generateReport(AdminDataModel.getClassObjectList());
			}
		};

		AdminDataModel.EVENT_BUS.addHandler(ClassObjectListUpdateEvent.TYPE,
				handler);

	}

	public void generateReport(List<ClassObject> classSummaryList) {
		setRowData(classSummaryList);
	}

}
