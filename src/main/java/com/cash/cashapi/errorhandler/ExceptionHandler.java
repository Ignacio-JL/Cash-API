package com.cash.cashapi.errorhandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandler {
	protected static final Logger logger = LogManager.getLogger(ExceptionHandler.class);
	
	@ResponseBody
	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	void ExceptionHanlder (Exception e) {
		System.setProperty("adv", "CASH API advertencia: ");
		logger.error(e.getCause(), e);
	}
	
	
	
}
