package com.insightech.onlinebeacon.admin.event.events;

import com.google.gwt.event.shared.GwtEvent;
import com.insightech.onlinebeacon.admin.event.handlers.SampleEventHandler;

public class SampleEvent extends GwtEvent<SampleEventHandler> {

	public static final Type<SampleEventHandler> TYPE = new Type<SampleEventHandler>();

	@Override
	public Type<SampleEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(SampleEventHandler handler) {
		handler.onEvent();
	}

}
