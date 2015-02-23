package com.vspace.yace.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vspace.yace.dao.TimesheetDAO;
import com.vspace.yace.domain.TimeEntry;
import com.vspace.yace.domain.Timesheet;
import com.vspace.yace.domain.User;
import com.vspace.yace.util.DateUtil;

public class TimesheetService {

	@Autowired
	TimesheetDAO timesheetDAO;

	public void saveTimesheet(User user, Timesheet timesheet,
			List<TimeEntry> timeEntryList) {
		
		timesheetDAO.saveTimesheet(user, timesheet, timeEntryList);		
	}

	public List<Timesheet> getCurrWeekTimesheets() {
		// Get current week start date;
		Date currWeekStartDate  = DateUtil.getClarityWeekStartDate();
		
		// Get timesheets of current week
		return getTimesheets(currWeekStartDate);
	}
	
	public List<Timesheet> getTimesheets(Date weekStartDate){
		return this.timesheetDAO.getTimesheets(weekStartDate);
	}

	private Date getCurrWeekStartDate() {
		// TODO Auto-generated method stub
		return null;
	}
	

	public Timesheet getCurrWeekTimesheetWithTimeEntries(Integer userId) {
		
		// Get current week start date;
		Date currWeekStartDate  = DateUtil.getClarityWeekStartDate();
		
		// Get timesheet of the user for current week
		return this.timesheetDAO.getTimesheetWithTimeEntries(currWeekStartDate, userId);
	}
	
	public static void main(String[] args) {
		
		Calendar cal = Calendar.getInstance();
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		int change = (dayOfWeek == 7 ? 0: dayOfWeek);
		
		// Set calendar to previous Saturday (starging day of Clarity week 
		cal.add(Calendar.DAY_OF_MONTH, -change);
		System.out.println("date--->"+ cal.getTime());
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
		System.out.println(sdf.format(cal.getTime()));
	}	
}
