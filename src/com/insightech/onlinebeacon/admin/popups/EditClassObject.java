package com.insightech.onlinebeacon.admin.popups;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.insightech.onlinebeacon.admin.AdminCommon;
import com.insightech.onlinebeacon.admin.AdminDataModel;
import com.insightech.onlinebeacon.shared.ClassObject;

public class EditClassObject extends DialogBox {

	private final class SaveButtonClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			classObject.setClassLocation(classLocationTextBox.getValue());
			classObject.setClassName(classNameTextBox.getValue());
			classObject.setClassDate(classDateBox.getValue());
			// TODO BUG Date picker doesn't work in Safari.
			classObject.setGames(gamesListBox.getSelectedIndex());
			List<Integer> newTeamCountList = new ArrayList<Integer>();
			for (TeamListBox listBox : listBoxList) {
				newTeamCountList.add(listBox.getTeams());
			}
			classObject.replaceTeamCountList(newTeamCountList);
			thisPopup.hide();
			AdminDataModel.saveClassObject(classObject);
		}
	}

	private class TeamListBox extends ListBox {

		public TeamListBox() {
			for (int team = 2; team <= AdminCommon.list.size(); team++) {
				addItem("" + team);
			}
		}

		@Override
		public void setSelectedIndex(int index) {
			super.setSelectedIndex(index - 2);
		}

		public Integer getTeams() {
			return Integer.parseInt(getItemText(getSelectedIndex()));
		}
	}

	private final class GameChangeHandler implements ChangeHandler {
		private final ClassObject classObject;

		private GameChangeHandler(ClassObject co) {
			classObject = co;
		}

		@Override
		public void onChange(ChangeEvent event) {
			boolean confirm = Window
					.confirm("You are changing the number of games. "
							+ "All previons games will be wiped out. "
							+ "Do you want to proceed?");
			if (confirm) {
				List<Integer> currentTeamCountList = new ArrayList<Integer>();
				int gameSelection = Integer.parseInt(gamesListBox
						.getItemText(gamesListBox.getSelectedIndex()));
				for (int game = 0; game < gameSelection; game++) {
					currentTeamCountList.add(new Integer(2));
				}
				setupTeamPanel(currentTeamCountList);
			} else {
				gamesListBox.setSelectedIndex(classObject.getGames());
				setupTeamPanel(teamCountList);
			}
		}
	}

	private class MediumFormat extends DateBox.DefaultFormat {

		@Override
		public String format(DateBox box, Date date) {
			if (date == null)
				return "";
			else
				return AdminCommon.dateFormater.format(date);
		}
	}

	private final VerticalPanel vp = new VerticalPanel();
	private final FlexTable table = new FlexTable();
	private final TextBox classNameTextBox = new TextBox();
	private final TextBox classLocationTextBox = new TextBox();
	private final DateBox classDateBox = new DateBox();
	private ListBox gamesListBox = new ListBox();
	private final HorizontalPanel buttonPanel = new HorizontalPanel();
	private final Button saveButton = new Button("Save to server");
	private final Button resetButton = new Button("Reset");
	private final Button cancelButton = new Button("Cancel");
	private EditClassObject thisPopup;
	private ClassObject classObject;
	private CaptionPanel teamSelectionPanel = new CaptionPanel(
			"Select the number of teams for each game.");
	private FlexTable teamTable = new FlexTable();
	private List<Integer> teamCountList;
	private List<TeamListBox> listBoxList;

	public EditClassObject(final ClassObject co) {
		thisPopup = this;
		classObject = co;
		setText("Edit Class Record");
		setModal(true);
		setGlassEnabled(true);
		setAnimationEnabled(true);
		buttonPanel.setSpacing(5);
		table.setCellSpacing(5);

		setWidget(vp);
		vp.add(table);
		vp.add(teamSelectionPanel);
		vp.add(buttonPanel);

		teamCountList = co.getTeamCountList();

		setupGamesListBox();

		classDateBox.setFormat(new MediumFormat());

		setupForm();

		teamSelectionPanel.add(teamTable);

		setupButtonPanel();

		center();
	}

	public void setupGamesListBox() {
		for (int i = 0; i <= 10; i++) {
			gamesListBox.addItem("" + i);
		}
		gamesListBox.setSelectedIndex(0);

		gamesListBox.addChangeHandler(new GameChangeHandler(classObject));
	}

	public void setupTeamPanel(List<Integer> teamCountList) {
		teamTable.removeAllRows();
		int numberOfGames = teamCountList.size();
		listBoxList = new ArrayList<EditClassObject.TeamListBox>();
		for (int gameNumber = 0; gameNumber < numberOfGames; gameNumber++) {
			TeamListBox teamListBox = new TeamListBox();
			teamListBox.setSelectedIndex(teamCountList.get(gameNumber));
			listBoxList.add(teamListBox);
			teamTable.setText(gameNumber, 0, "Game #" + (gameNumber + 1));
			teamTable.setWidget(gameNumber, 1, listBoxList.get(gameNumber));
		}
	}

	public void setupButtonPanel() {
		buttonPanel.add(saveButton);
		saveButton.addClickHandler(new SaveButtonClickHandler());

		buttonPanel.add(resetButton);
		resetButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				thisPopup.hide();
				reset();
				thisPopup.center();
			}
		});

		buttonPanel.add(cancelButton);
		cancelButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				thisPopup.hide();
			}
		});

	}

	public void setupForm() {
		table.setText(0, 0, "Class Name:");
		table.setText(1, 0, "Location");
		table.setText(2, 0, "Date");
		table.setText(3, 0, "Games");

		reset();
	}

	public void reset() {
		classNameTextBox.setValue(classObject.getClassName());
		table.setWidget(0, 1, classNameTextBox);
		classLocationTextBox.setValue(classObject.getClassLocation());
		table.setWidget(1, 1, classLocationTextBox);
		classDateBox.setValue(classObject.getClassDate());
		table.setWidget(2, 1, classDateBox);
		gamesListBox.setSelectedIndex(classObject.getGames());
		table.setWidget(3, 1, gamesListBox);
		setupTeamPanel(teamCountList);
	}

}
