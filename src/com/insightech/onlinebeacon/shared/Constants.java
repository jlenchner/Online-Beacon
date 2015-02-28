package com.insightech.onlinebeacon.shared;

import java.util.Arrays;
import java.util.List;

public class Constants {

	public static List<String> TEAM_NAMES_LIST = Arrays.asList("North",
			"South", "East", "West", "Central", "North East", "North West",
			"South East", "South West");

	public static List<String> GET_TEAM_NAME_SUBLIST(int count) {
		return TEAM_NAMES_LIST.subList(0, count - 1);
	}

	public static String[] GET_TEAM_NAME_ARRAY(int count) {
		String[] array = new String[count];
		for (int i = 0; i < array.length; i++) {
			array[i] = TEAM_NAMES_LIST.get(i);
		}
		return array;
	}
}
