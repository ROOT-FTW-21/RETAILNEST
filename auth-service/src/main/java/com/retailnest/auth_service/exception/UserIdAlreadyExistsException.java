package com.retailnest.auth_service.exception;

public class UserIdAlreadyExistsException extends RuntimeException {
	public UserIdAlreadyExistsException (String message) {super(message);}
}
