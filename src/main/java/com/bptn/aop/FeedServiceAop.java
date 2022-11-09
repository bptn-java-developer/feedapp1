package com.bptn.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bptn.exceptions.InvalidUserNameException;
import com.bptn.service.UserService;

@Aspect
@Component
public class FeedServiceAop {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserService userService;
	
	@Before(value = "execution(* com.bptn.service.FeedService.getPostsbyUsername(..)) and args(username)")
	public void beforeAdviceOneParam(JoinPoint joinPoint, String username) throws InvalidUserNameException {
		
		logger.debug("Before method:" + joinPoint.getSignature());
		logger.debug("Looking for User with username: {}", username);
		
		this.userService.validateUserId(username);
		
	}

	@After(value = "execution(* com.bptn.service.FeedService.getPostsbyUsername(..)) and args(username)")
	public void afterAdviceOneParam(JoinPoint joinPoint, String username) {
		
		logger.debug("After method:" + joinPoint.getSignature());
		logger.debug("Successfully looked for User with username: {}", username);
	}	
	
	@Before(value = "execution(* com.bptn.service.FeedService.getPostsByPostId(..))")
	public void beforeAdviceMethodName(JoinPoint joinPoint) {
		
		logger.debug("Before method:" + joinPoint.getSignature());
		logger.debug("Calling method().");		
	}
	
	@After(value = "execution(* com.bptn.service.FeedService.getPostsByPostId(..))")
	public void afterAdviceMethodName(JoinPoint joinPoint) {
		
		logger.debug("After method:" + joinPoint.getSignature());
		logger.debug("Finishing method().");
	}
}
