package com.retailnest.product_service.exception.handler;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.retailnest.product_service.exception.AccessUnauthorizedException;
import com.retailnest.product_service.exception.ProductNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	private static final String MESSAGE = "message";
	
	@ExceptionHandler(AccessUnauthorizedException.class)
	public ResponseEntity<Map<String, String>> handleAccessUnauthorizedException(String message) {
		Map<String, String> errorsMap = new HashMap<String, String>();
		errorsMap.put(MESSAGE, message);
		LOGGER.warn(message);
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorsMap);
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleProductNotFoundException(String message){
		Map<String, String> errorsMap = new HashMap<String, String>();
		errorsMap.put(MESSAGE, message);
		LOGGER.warn(message);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorsMap);
	}
}
