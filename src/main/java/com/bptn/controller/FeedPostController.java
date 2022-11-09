package com.bptn.controller;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bptn.exceptions.InvalidRequestException;
import com.bptn.exceptions.InvalidUserNameException;
import com.bptn.jpa.Post;
import com.bptn.request.FeedPostRequest;
import com.bptn.service.FeedPostService;
import com.bptn.service.UserService;

@RestController
public class FeedPostController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	FeedPostService feedPostService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/posts/feed/create")
	public ResponseEntity<?> saveFeed(@RequestBody FeedPostRequest request) {
		
		logger.debug("Executing saveFeed API");
		
		try {
			this.validateRequest(request);
			
			this.userService.validateUserId(request.getUsername());

			Post feed = this.feedPostService.getPostFormNewsAndSavePost(request);
			
			logger.debug("Post Saved Successfully");
			
			return new ResponseEntity<>(feed, HttpStatus.OK);
					
		} catch (InvalidRequestException | InvalidUserNameException ex) {

			logger.error(ex.getMessage(),ex);
			
			return ResponseEntity.badRequest().body(ex.getMessage());
		}		
	}

	@PostMapping("/posts/feed/create2")
	public Post saveFeed2(@RequestBody FeedPostRequest request)
			throws InvalidRequestException, InvalidUserNameException {

		logger.debug("Executing saveFeed2 API");
		
		this.validateRequest(request);

		this.userService.validateUserId(request.getUsername());

		Post feed = this.feedPostService.getPostFormNewsAndSavePost(request);

		logger.debug("Post Saved Successfully");

		return feed;

	}
	
	private void validateRequest(FeedPostRequest request) throws InvalidRequestException {
		
		if ( !StringUtils.hasText(request.getFromDate()) ||
			 !StringUtils.hasText(request.getToDate())) {
			throw new InvalidRequestException("Invalid request: FromDate and ToDate are required");
		}	
	}

	private void validateRequest2(FeedPostRequest request) throws InvalidRequestException {
		
		if ( (request.getFromDate()!=null && request.getFromDate().trim().isBlank()) ||
			 (request.getToDate()!=null && request.getToDate().trim().isBlank())) {
			
			throw new InvalidRequestException("Invalid request: FromDate and ToDate are required");
		}	
	}
}
