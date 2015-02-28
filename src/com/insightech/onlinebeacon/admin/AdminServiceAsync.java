package com.insightech.onlinebeacon.admin;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.insightech.onlinebeacon.admin.reports.TeamSummaryObject;
import com.insightech.onlinebeacon.shared.ClassObject;
import com.insightech.onlinebeacon.shared.GameStatusObject;
import com.insightech.onlinebeacon.shared.ResultsCollection;

public interface AdminServiceAsync {

	void ping(AsyncCallback<Integer> callback);

	void getClassObject(Long classNumber, AsyncCallback<ClassObject> callback);

	void saveClassObject(ClassObject classObject,
			AsyncCallback<List<ClassObject>> callback);

	void deleteClassFromDatabase(Long classNumber,
			AsyncCallback<List<ClassObject>> callback);

	void getClassObjectList(AsyncCallback<List<ClassObject>> callback);

	void startClass(Long classNumber, AsyncCallback<Void> callback);

	void getGameSummaryObjectList(Long classNumber,
			AsyncCallback<List<GameStatusObject>> callback);

	void getTeamSummaryObjectList(Long classNunumber, Long gameNumber,
			AsyncCallback<List<TeamSummaryObject>> callback);

	void getResults(Long classNumber, Long gameNumber,
			AsyncCallback<ResultsCollection> callback);

}
