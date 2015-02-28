package com.insightech.onlinebeacon.admin;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.insightech.onlinebeacon.admin.reports.TeamSummaryObject;
import com.insightech.onlinebeacon.shared.ClassObject;
import com.insightech.onlinebeacon.shared.GameStatusObject;
import com.insightech.onlinebeacon.shared.ResultsCollection;

@RemoteServiceRelativePath("admin")
public interface AdminService extends RemoteService {
	int ping();

	ClassObject getClassObject(Long classNumber);

	List<ClassObject> saveClassObject(ClassObject classObject);

	List<ClassObject> getClassObjectList();

	List<GameStatusObject> getGameSummaryObjectList(Long classNumber);

	List<TeamSummaryObject> getTeamSummaryObjectList(Long classNumber,
			Long gameNumber);

	ResultsCollection getResults(Long classNumber, Long gameNumber);

	List<ClassObject> deleteClassFromDatabase(Long classNumber);

	void startClass(Long classNumber);
}
