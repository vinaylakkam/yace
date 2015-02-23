package com.vspace.yace.command;

import java.util.ArrayList;
import java.util.List;

import com.vspace.yace.domain.Timesheet;

public class Timesheets {

	List<Timesheet> timesheets;

	public List<Timesheet> getTimeSheetList() {
		
		if(timesheets == null){
			timesheets = new ArrayList<Timesheet>();
		}
		
		return timesheets;
	}
}
