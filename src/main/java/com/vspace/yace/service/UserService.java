package com.vspace.yace.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import com.vspace.yace.dao.UserDAO;
import com.vspace.yace.domain.User;

// TODO: Make it as @Service
//TODO: This needs to be an interface, UserDAO will implement this; as it is just forwarding the request. 
public class UserService {

	@Autowired
	UserDAO userDAO;

	public User loadUser(String clientIP) {
		return userDAO.loadUser(clientIP);
	}
	
	public void saveUser(User user) {
		userDAO.saveUser(user);
	}

	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}
	
	
}
