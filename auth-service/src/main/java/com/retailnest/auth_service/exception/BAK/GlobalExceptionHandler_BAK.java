package com.retailnest.auth_service.exception.BAK;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.retailnest.auth_service.exception.RuntimeExceptions;
import com.retailnest.auth_service.exception.payload.ApiExceptionPayload;

@RestControllerAdvice
public class GlobalExceptionHandler_BAK {
    @ExceptionHandler(RuntimeExceptions.class)
    public ResponseEntity<ApiExceptionPayload> resourceNotFoundExceptionHandler(RuntimeExceptions exception) {
        String message = exception.getMessage();
        ApiExceptionPayload apiExceptionPayload = new ApiExceptionPayload(message, false, HttpStatus.NOT_FOUND);
        return new ResponseEntity<ApiExceptionPayload>(apiExceptionPayload, HttpStatus.NOT_FOUND);
    }
}
