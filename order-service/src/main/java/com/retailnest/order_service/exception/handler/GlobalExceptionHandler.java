package com.retailnest.order_service.exception.handler;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.retailnest.order_service.exception.AccessUnauthorizedException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	private static final String MESSAGE = "message";

	@ExceptionHandler(AccessUnauthorizedException.class)
	public ResponseEntity<Map<String, String>> handleAccessUnauthorizedException(String message) {
		Map<String, String> errors = new HashMap<String, String>();
		errors.put(MESSAGE, message);

		LOGGER.warn(message);

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errors);
	}
}
