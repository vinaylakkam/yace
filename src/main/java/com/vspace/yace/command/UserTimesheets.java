package com.vspace.yace.command;

import java.util.ArrayList;
import java.util.List;

import com.vspace.yace.command.UserTimesheet;

public class UserTimesheets {

	private List<UserTimesheet> userTimesheets;

	public List<UserTimesheet> getUserTimesheetList() {
		if (userTimesheets == null) {
			userTimesheets = new ArrayList<UserTimesheet>();
		}
		return userTimesheets;
	}
}
