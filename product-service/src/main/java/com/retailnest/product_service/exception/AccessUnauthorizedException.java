package com.retailnest.product_service.exception;

public class AccessUnauthorizedException extends RuntimeException {
	public AccessUnauthorizedException(String message) {
		super(message);
	}
}
