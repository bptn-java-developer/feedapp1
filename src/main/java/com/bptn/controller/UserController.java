package com.bptn.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bptn.bean.UserID;
import com.bptn.service.UserService;

@RestController
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserService userService;

	@GetMapping(value = "/users")
	public List<UserID> listUsers() {
		
	    logger.debug("Listing Users with JDBC!");

	    List<UserID> users = this.userService.listUsers();
	
	    return users;
	}

	@GetMapping(value = "/users/{username}")
	public UserID findUser(@PathVariable String username) {
		
		logger.debug("Finding User with JDBC, username: {}");
		
		UserID user = this.userService.findUser(username);
		
		return user;
	}

	@PostMapping(value = "/users/create")
	public String createUser(@RequestBody UserID user) {
		
		logger.debug("Creating User with JDBC, {}",user);
		
		this.userService.createUser(user);
		
		return "User Created Successfully";
	}
}
