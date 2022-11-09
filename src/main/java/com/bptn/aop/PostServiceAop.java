package com.bptn.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PostServiceAop {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Before(value = "execution(* com.bptn.service.PostService.getPostsByUsername(..)) and args(username)")
	public void beforeAdviceOneParam(JoinPoint joinPoint, String username) {
		
		logger.debug("Before method:" + joinPoint.getSignature());
		logger.debug("Looking for User with username: {}", username);
		
	}

	@After(value = "execution(* com.bptn.service.PostService.getPostsByUsername(..)) and args(username)")
	public void afterAdviceOneParam(JoinPoint joinPoint, String username) {
		
		logger.debug("After method:" + joinPoint.getSignature());
		logger.debug("Successfully looked for User with username: {}", username);
	}	
	
	@Before(value = "execution(* com.bptn.service.PostService.getPostsByPostId(..))")
	public void beforeAdviceMethodName(JoinPoint joinPoint) {
		
		logger.debug("Before method:" + joinPoint.getSignature());
		logger.debug("Calling method().");		
	}
	
	@After(value = "execution(* com.bptn.service.PostService.getPostsByPostId(..))")
	public void afterAdviceMethodName(JoinPoint joinPoint) {
		
		logger.debug("After method:" + joinPoint.getSignature());
		logger.debug("Finishing method().");
	}
}
