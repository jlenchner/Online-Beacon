package com.insightech.onlinebeacon.admin.event.events;

import com.google.gwt.event.shared.GwtEvent;
import com.insightech.onlinebeacon.admin.event.handlers.ResultsCollectionUpdateHandler;

public class ResultsCollectionUpdateEvent extends
		GwtEvent<ResultsCollectionUpdateHandler> {

	public static final Type<ResultsCollectionUpdateHandler> TYPE = new Type<ResultsCollectionUpdateHandler>();

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ResultsCollectionUpdateHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ResultsCollectionUpdateHandler handler) {
		handler.onUpdate();
	}

}
