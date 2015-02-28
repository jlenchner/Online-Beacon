package com.insightech.onlinebeacon.admin.event.events;

import com.google.gwt.event.shared.GwtEvent;
import com.insightech.onlinebeacon.admin.event.handlers.GameStatusListUpdateHandler;

public class GameStatusListUpdateEvent extends
		GwtEvent<GameStatusListUpdateHandler> {

	public static final Type<GameStatusListUpdateHandler> TYPE = new Type<GameStatusListUpdateHandler>();

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<GameStatusListUpdateHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(GameStatusListUpdateHandler handler) {
		handler.onUpdate();
	}

}
