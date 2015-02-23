package com.vspace.yace.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vspace.yace.domain.User;
import com.vspace.yace.service.UserService;
import com.vspace.yace.validation.UserValidator;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(HttpServletRequest request, Model model) {
		
		// Get IP address of the requested system
		String clientIP = request.getRemoteAddr();
		
		System.out.println("clientIP--->" + clientIP); 
		
		// Load the user, if already exists
		User user = userService.loadUser(clientIP);
		
		// Add user to model
		model.addAttribute("user", user);
		
		// Return jsp
		return "user";
	}
	

	@RequestMapping(method = RequestMethod.POST)
	public String processAddUser(@ModelAttribute("user") User user, BindingResult result) {
		
		// Validate form, before proceeding
		new UserValidator().validate(user, result);
		if (result.hasErrors()) {
			// Show the same page, when form has errors
			return "user";
		}
		
		System.out.println("To save user...");
		this.userService.saveUser(user);
		//status.setComplete();
		//return "redirect:/user/new";
	
		return "user";
		//return "User "+ user.getId() +" added..."; 
	}	
}
