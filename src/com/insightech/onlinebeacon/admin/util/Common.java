package com.insightech.onlinebeacon.admin.util;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

public class Common {

	public static Integer getYear(int yearIndex) {
		return getYear() + yearIndex;
	}

	static public Integer getYear() {
		DateTimeFormat yearFormat = DateTimeFormat.getFormat("yyyy");
		String yearString = yearFormat.format(new Date());
		Integer year = new Integer(yearString);
		return year;
	}

}
