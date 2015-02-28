package com.insightech.onlinebeacon.admin;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.i18n.shared.DateTimeFormat.PredefinedFormat;

public interface AdminCommon {
	final DateTimeFormat dateFormater = DateTimeFormat
			.getFormat(PredefinedFormat.DATE_MEDIUM);

	final List<String> list = Arrays.asList("North", "South", "East", "West",
			"Central", "Northeast", "Northwest", "Southeast", "Southwest");

}
