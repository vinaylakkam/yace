package com.vspace.yace.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.vspace.yace.command.UserTimesheet;
import com.vspace.yace.dao.UserDAO;
import com.vspace.yace.domain.Timesheet;
import com.vspace.yace.domain.User;
import com.vspace.yace.util.EntityUtil;

// TODO: Make it as @Service
public class AdminService {

	@Autowired
	UserService userService;
	
	@Autowired
	TimesheetService timesheetService;

	
	

	public List<UserTimesheet> getCurrWeekUserTimesheets() {
		
		// Load all users
		List<User> users = this.userService.getAllUsers();
		
		// Sort users by name
		Collections.sort(users, new Comparator<User>(){
			@Override
			public int compare(User user1, User user2) {
				return user1.getfName().compareTo(user2.getfName());
			}
			
		});
		
		// Load timesheet s of all users
		List<Timesheet> timesheets = this.timesheetService.getCurrWeekTimesheets();

		// Update in UserTimesheet
		List<UserTimesheet> userTimesheets = new ArrayList<UserTimesheet>();
		for(User user: users){
			System.out.println(user.getId());
			UserTimesheet userTimesheet = new UserTimesheet();
			
			// Add user
			userTimesheet.setUser(user);
			
			// Find the corresponding timesheet and add
			Timesheet timesheet = EntityUtil.getByUserId(timesheets, Timesheet.class, user.getId());
			if(timesheet != null) {
				System.out.println("user found:"+ user.getId());
				userTimesheet.setTimesheet(timesheet);
			}
			else {
				userTimesheet.setDefaulter(true);
			}
			
			// Add to list
			userTimesheets.add(userTimesheet);
		}
		
		return userTimesheets;
	}
	
	
}
