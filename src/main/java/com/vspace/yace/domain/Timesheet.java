package com.vspace.yace.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

import com.vspace.yace.command.JsonDateDeserializer;

public class Timesheet extends UserRelatedDO {


	String status;
	String clarityStatus;
	Date lastModifDate;
	Date weekStartDate;
	Integer modifBy;
	
	Double day1Hrs;
	Double day2Hrs;
	Double day3Hrs;
	Double day4Hrs;
	Double day5Hrs;
	Double day6Hrs;
	Double day7Hrs;
	Double totalHrs;
	
	// List of time entries for this time sheet.
	List<TimeEntry> timeEntries;
	
	
	public List<TimeEntry> getTimeEntries() {
		if(timeEntries == null) {
			timeEntries = new ArrayList<TimeEntry>();
		}
		return timeEntries;
	}

	public void setTimeEntries(List<TimeEntry> timeEntries) {
		this.timeEntries = timeEntries;
	}



	public Timesheet() {
		super();
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getClarityStatus() {
		return clarityStatus;
	}


	public void setClarityStatus(String clarityStatus) {
		this.clarityStatus = clarityStatus;
	}


	public Date getLastModifDate() {
		return lastModifDate;
	}

	@JsonDeserialize(using=JsonDateDeserializer.class)
	public void setLastModifDate(Date lastModifDate) {
		this.lastModifDate = lastModifDate;
	}


	public Date getWeekStartDate() {
		return weekStartDate;
	}

	@JsonDeserialize(using=JsonDateDeserializer.class)
	public void setWeekStartDate(Date weekStartDate) {
		this.weekStartDate = weekStartDate;
	}


	public Integer getModifBy() {
		return modifBy;
	}


	public void setModifBy(Integer modifBy) {
		this.modifBy = modifBy;
	}


	public Double getDay1Hrs() {
		return day1Hrs;
	}


	public void setDay1Hrs(Double day1Hrs) {
		this.day1Hrs = day1Hrs;
	}


	public Double getDay2Hrs() {
		return day2Hrs;
	}


	public void setDay2Hrs(Double day2Hrs) {
		this.day2Hrs = day2Hrs;
	}


	public Double getDay3Hrs() {
		return day3Hrs;
	}


	public void setDay3Hrs(Double day3Hrs) {
		this.day3Hrs = day3Hrs;
	}


	public Double getDay4Hrs() {
		return day4Hrs;
	}


	public void setDay4Hrs(Double day4Hrs) {
		this.day4Hrs = day4Hrs;
	}


	public Double getDay5Hrs() {
		return day5Hrs;
	}


	public void setDay5Hrs(Double day5Hrs) {
		this.day5Hrs = day5Hrs;
	}


	public Double getDay6Hrs() {
		return day6Hrs;
	}


	public void setDay6Hrs(Double day6Hrs) {
		this.day6Hrs = day6Hrs;
	}


	public Double getDay7Hrs() {
		return day7Hrs;
	}


	public void setDay7Hrs(Double day7Hrs) {
		this.day7Hrs = day7Hrs;
	}


	public Double getTotalHrs() {
		return totalHrs;
	}


	public void setTotalHrs(Double totalHrs) {
		this.totalHrs = totalHrs;
	}

}
