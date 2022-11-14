package com.bptn.controller;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bptn.service.MailService;

@RestController
public class MailController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MailService mailService;

	@GetMapping(value = "/sendemail")
	public String sendEmail() throws AddressException, MessagingException {
		
	    logger.debug("Sending Email");

	    this.mailService.sendEmail("cgzegarra@gmail.com");
	
	    return "Email sent successfully";
	}

}
