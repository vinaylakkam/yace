package com.vspace.yace.controllers;

import java.util.Collection;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

import com.vspace.yace.command.ClarityJsonModel;
import com.vspace.yace.command.ClarityJsonModel;
import com.vspace.yace.domain.TimeEntry;
import com.vspace.yace.domain.Timesheet;
import com.vspace.yace.domain.User;
import com.vspace.yace.service.TimesheetService;
import com.vspace.yace.service.UserService;
import com.vspace.yace.util.DateUtil;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	TimesheetService timesheetService;	
	
	@ModelAttribute("currWeekDates")
	public Collection<String> populateWeekDates(){
		return DateUtil.getCurrentWeekDates();
	}
	
    @RequestMapping(value="", method = RequestMethod.GET)
    public String setupForm(HttpServletRequest request, Model model) {

    	System.out.println(request.getRequestURI().substring(5));
		// Get IP address of the client
		String clientIP = request.getRemoteAddr();
		
		System.out.println("clinetIP-->" + clientIP);
		
		// Load the user, if already exists
		User user = userService.loadUser(clientIP);
		
		if(user.isNew()){
			String sourceUrl = request.getRequestURI().substring(5);
			// Show register screen.
			return "redirect:/user?sourceUrl="+sourceUrl ;
		}
		
		// Load timesheet of user
		Timesheet timesheet = this.timesheetService.getCurrWeekTimesheetWithTimeEntries(user.getId());
		if(!timesheet.isNew()) {
			// Timesheet already exist for the user for current week
		}
		
		// Add objects to model
		model.addAttribute("user", user); 
		model.addAttribute("timesheet", timesheet); 
		
    	return "home";
    }
    
    @ResponseBody
    @RequestMapping(value="save", method = RequestMethod.POST)
    public String saveClarityData(@RequestBody ClarityJsonModel clarityJSON){
    	
    	System.out.println("Save recieved");
    	
    	User user = clarityJSON.getUser();
    	Timesheet timesheet = clarityJSON.getTimesheet();
    	List<TimeEntry> timeEntryList = clarityJSON.getTimeEntry();
    	
    	// Save timesheet
    	timesheetService.saveTimesheet(user, timesheet, timeEntryList);
    	
    	System.out.println("SAVED ..SAVED ..SAVED ..");
    	
    	return "saved";
    }
    
    @RequestMapping(value="faq")
    public String faq() {
    	return "faq";
    }
}
