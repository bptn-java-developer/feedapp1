package com.bptn.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bptn.exceptions.InvalidRequestException;
import com.bptn.exceptions.InvalidUserNameException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//@Autowired
	//HttpRequest request;
	
	@ExceptionHandler(InvalidUserNameException.class)
	public ResponseEntity<String> handleInvalidUserExceptions(Exception ex, WebRequest request){
	
		logger.debug("Error with the username");
		logger.debug(ex.getMessage(),ex);
	
		//request.
		
		return ResponseEntity.badRequest().body(ex.getMessage());
	}

	@ExceptionHandler(InvalidRequestException.class)
	public ResponseEntity<String> handleInvalidRequestExceptions(Exception ex, WebRequest request){
		
		logger.debug(ex.getMessage(),ex);
		
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
}
