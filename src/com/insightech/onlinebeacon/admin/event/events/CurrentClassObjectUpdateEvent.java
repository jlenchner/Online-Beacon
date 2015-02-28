package com.insightech.onlinebeacon.admin.event.events;

import com.google.gwt.event.shared.GwtEvent;
import com.insightech.onlinebeacon.admin.AdminDataModel;
import com.insightech.onlinebeacon.admin.event.handlers.ClassObjectUpdateHandler;

public class CurrentClassObjectUpdateEvent extends GwtEvent<ClassObjectUpdateHandler> {
	public static final Type<ClassObjectUpdateHandler> TYPE = new Type<ClassObjectUpdateHandler>();

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ClassObjectUpdateHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ClassObjectUpdateHandler handler) {
		handler.onUpdate(AdminDataModel.getCurrentClassObject());
	}

}
