package com.vspace.yace.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class DateUtil {



	/**
	 * Get start day (SaturDay)of the clarity week
	 * @return
	 */
	public static Date getClarityWeekStartDate() {
		
		Calendar cal = Calendar.getInstance();

		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		int change = (dayOfWeek == 7 ? 0: dayOfWeek);

		// Set calendar to previous Saturday (starting day of Clarity week) 
		cal.add(Calendar.DAY_OF_MONTH, -change);
		System.out.println("date--->"+ cal.getTime());

		return cal.getTime();
	}
	
	/**
	 * Get start day (SaturDay)of the clarity week for the given date
	 * 
	 * @param date
	 * @return
	 */
	public static Date getClarityWeekStartDate(String date) {
		
		Calendar cal = Calendar.getInstance();
		
		//TODO
		return cal.getTime();
	}

	public static Collection<String> getCurrentWeekDates() {
		
		Calendar cal = Calendar.getInstance();

		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		int change = (dayOfWeek == 7 ? 0: dayOfWeek);

		// Set calendar to previous Saturday (starting day of Clarity week) 
		cal.add(Calendar.DAY_OF_MONTH, -change);
		System.out.println("date--->"+ cal.getTime());
		
		// Starting from 1st day, add dates to the collections
		List<String> dates = new ArrayList<String>();
		for (int i=0; i<7; i++) {
			
			// Add date to collection
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
			String date = sdf.format(cal.getTime());
			dates.add(date);
			
			// Goto next day
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		return dates;
	}
	
	public static void main(String[] args){
		
		Collection<String> dates = getCurrentWeekDates();
		
		for (String s: dates) {
			System.out.println(s);
		}
	}

}
