package com.vspace.yace.command;

import com.vspace.yace.domain.Timesheet;
import com.vspace.yace.domain.User;

public class UserTimesheet {

	User user;
	Timesheet timesheet;
	boolean defaulter;
	
	
	public boolean isDefaulter() {
		return defaulter;
	}
	public void setDefaulter(boolean defaulter) {
		this.defaulter = defaulter;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Timesheet getTimesheet() {
		return timesheet;
	}
	public void setTimesheet(Timesheet timesheet) {
		this.timesheet = timesheet;
	}
	
	
	
}
