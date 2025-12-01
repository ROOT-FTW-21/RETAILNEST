package com.retailnest.auth_service.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidException(MethodArgumentNotValidException exception) {
        log.warn(exception.getMessage());

        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleAlreadyExistsException() {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Email already exists");
        log.warn(errors.get("message"));

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> UserNotFoundException() {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Patient not found");
        log.warn(errors.get("message"));

        return ResponseEntity.badRequest().body(errors);
    }
    
    @ExceptionHandler(InvalidMobileNumberException.class)
    public ResponseEntity<Map<String, String>> handleInvalidMobileNumberException(InvalidMobileNumberException exception) {
    	Map<String, String> errors = new HashMap<String, String>();
    	errors.put("message", exception.getMessage());
    	log.warn(exception.getMessage());
    	
    	return ResponseEntity.badRequest().body(errors);
    }
    
    @ExceptionHandler(MobileAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleMobileAlreadyExistsException(MobileAlreadyExistsException exception) {
    	Map<String, String> errors = new HashMap<String, String>();
    	errors.put("message", exception.getMessage());
    	log.warn(exception.getMessage());
    	
    	return ResponseEntity.badRequest().body(errors);
    }
    
    @ExceptionHandler(UserIdAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleUserIdAlreadyExistsException(UserIdAlreadyExistsException exception) {
    	Map<String, String> errors = new HashMap<String, String>();
    	errors.put("message", exception.getMessage());
    	log.warn(exception.getMessage());
    	
    	return ResponseEntity.badRequest().body(errors);
    }
    
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Map<String, String>> handleUnauthorizedException(UnauthorizedException exception) {
    	Map<String, String> errors = new HashMap<String, String>();
    	errors.put("message", exception.getMessage());
    	log.warn(exception.getMessage());
    	
    	return ResponseEntity.badRequest().body(errors);
    }
}
