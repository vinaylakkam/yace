package com.vspace.yace.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vspace.yace.command.UserTimesheets;
import com.vspace.yace.command.Timesheets;
import com.vspace.yace.command.UserTimesheet;
import com.vspace.yace.domain.Timesheet;
import com.vspace.yace.domain.User;
import com.vspace.yace.service.AdminService;
import com.vspace.yace.service.TimesheetService;
import com.vspace.yace.service.UserService;
import com.vspace.yace.util.DateUtil;
import com.vspace.yace.util.EntityUtil;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;
 
	
	@ModelAttribute("currWeekDates")
	public Collection<String> populateWeekDates(){
		
		return DateUtil.getCurrentWeekDates();
	}
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ModelAndView setupAdmin(HttpServletRequest request){
		
		// Get IP address of the client
		String clientIP = request.getRemoteAddr();
		System.out.println("clinetIP-->" + clientIP); 
		// Load the user, if already exists
		//User loggedInUser = userService.loadUser(clientIP);
		
		// TODO: Check if the user has admin previleges
		
		List<UserTimesheet> userTimesheetList = adminService.getCurrWeekUserTimesheets();
		
		
		
		// Load timesheets of all users //TODO: check if this is good way
		/*UserTimesheets userTimesheets = new UserTimesheets();
		userTimesheets.getUserTimesheetList().addAll(userTimesheetList);
		*/
		
		Map<String, Object> map = new HashMap<String, Object>();
	    map.put("userTimesheets", userTimesheetList);
	      
	    return new ModelAndView("adminXL", map);
	    		
		
		/*ModelAndView mav =null;
		
		if(false) {
			mav = new ModelAndView("admin");
			mav.addObject("userTimesheets", userTimesheets);
		}
		else {
			mav = new ModelAndView("adminxl");
			mav.addObject("userTimesheets", userTimesheets);
		}
		
		return mav;*/
	}
}
