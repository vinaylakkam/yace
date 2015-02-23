package com.vspace.yace.domain;

import java.util.Date;

public class TimeEntry extends UserRelatedDO {

	Integer tsId;
	String projId;
	String projName;
	String projDesc;
	
	Double day1Hrs;
	Double day2Hrs;
	Double day3Hrs;
	Double day4Hrs;
	Double day5Hrs;
	Double day6Hrs;
	Double day7Hrs;
	Double totalHrs;
	
	public TimeEntry() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Integer getTsId() {
		return tsId;
	}

	public void setTsId(Integer tsId) {
		this.tsId = tsId;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getProjDesc() {
		return projDesc;
	}

	public void setProjDesc(String projDesc) {
		this.projDesc = projDesc;
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
