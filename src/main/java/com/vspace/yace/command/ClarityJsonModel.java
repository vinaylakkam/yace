package com.vspace.yace.command;

import java.util.List;

import com.vspace.yace.domain.TimeEntry;
import com.vspace.yace.domain.Timesheet;
import com.vspace.yace.domain.User;

public class ClarityJsonModel {
	
	private Timesheet timesheet;
	private List<TimeEntry> timeEntry;
	private User user;
	
	public Timesheet getTimesheet() {
		return timesheet;
	}
	public void setTimesheet(Timesheet timesheet) {
		this.timesheet = timesheet;
	}
	public List<TimeEntry> getTimeEntry() {
		return timeEntry;
	}
	public void setTimeEntry(List<TimeEntry> timeEntry) {
		this.timeEntry = timeEntry;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	

/*	private class Time {
		String weekStartDate;
		String status;
		List<TimeEntry> timeEntry;
		Timesheet timesheet;
	}
	
	private class TimeEntry{
		String projId;
		String projName;
		String projDesc;
		double day1Hrs;
		double day2Hrs;
		double day3Hrs;
		double day4Hrs;
		double day5Hrs;
		double day6Hrs;
		double day7Hrs;
		double totalHrs;
	}
	private class Timesheet{
		double day1Hrs;
		double day2Hrs;
		double day3Hrs;
		double day4Hrs;
		double day5Hrs;
		double day6Hrs;
		double day7Hrs;
		double totalHrs;
	}*/
}
