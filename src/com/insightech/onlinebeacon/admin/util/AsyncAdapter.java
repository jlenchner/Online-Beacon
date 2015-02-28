package com.insightech.onlinebeacon.admin.util;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class AsyncAdapter<T> implements AsyncCallback<T> {

	@Override
	public void onFailure(Throwable caught) {
		Window.alert("Call to server failed.");
	}

	@Override
	public abstract void onSuccess(T result);

}
