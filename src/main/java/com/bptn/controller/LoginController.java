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

import com.bptn.jpa.Post;
import com.bptn.jpa.UserID;
import com.bptn.service.LoginService;

@RestController
public class LoginController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	// Dependency Injection
	@Autowired
	LoginService loginService;
	
	@PostMapping("login")
	public String validateUserCredentials(@RequestBody UserID userID) {
	
		logger.debug("Validating Credentials: {}", userID);
		
		String response = this.loginService.validateUserCredentials(userID);
		
		return response;
	}

    @GetMapping(value = "/users/posts/username/{username}")
    public List<Post> getPostsByUsername(@PathVariable("username") String username) {
        
    	logger.debug("Executing getPostsByUsername API");
        
    	List<Post> posts = this.loginService.getPostsByUsername(username);

        return posts;
    }
	
}
