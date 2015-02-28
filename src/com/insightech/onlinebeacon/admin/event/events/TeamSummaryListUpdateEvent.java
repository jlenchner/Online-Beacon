package com.insightech.onlinebeacon.admin.event.events;

import com.google.gwt.event.shared.GwtEvent;
import com.insightech.onlinebeacon.admin.event.handlers.TeamSummaryListUpdatteHandler;

public class TeamSummaryListUpdateEvent extends
		GwtEvent<TeamSummaryListUpdatteHandler> {

	public static final Type<TeamSummaryListUpdatteHandler> TYPE = new Type<TeamSummaryListUpdatteHandler>();

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<TeamSummaryListUpdatteHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(TeamSummaryListUpdatteHandler handler) {
		handler.onUpdate();
	}

}
