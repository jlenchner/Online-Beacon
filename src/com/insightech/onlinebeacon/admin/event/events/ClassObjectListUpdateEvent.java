package com.insightech.onlinebeacon.admin.event.events;

import com.google.gwt.event.shared.GwtEvent;
import com.insightech.onlinebeacon.admin.event.handlers.ClassObjectListUpdateHandler;

public class ClassObjectListUpdateEvent extends
		GwtEvent<ClassObjectListUpdateHandler> {

	public static final Type<ClassObjectListUpdateHandler> TYPE = new Type<ClassObjectListUpdateHandler>();

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ClassObjectListUpdateHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ClassObjectListUpdateHandler handler) {
		handler.onUpdate();
	}

}
