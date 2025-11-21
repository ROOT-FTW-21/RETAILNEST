package com.retailnest.auth_service.exception.payload;

import org.springframework.http.HttpStatus;

public class ApiExceptionPayload {
    private String message;
    private boolean success;
    private HttpStatus httpStatus;

    public ApiExceptionPayload(String message, boolean success, HttpStatus httpStatus) {
        this.message = message;
        this.success = success;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    /*
     * public static class ApiResponseToBuilder { private String message; private
     * boolean success; private HttpStatus httpStatus;
     *
     *
     * public ApiResponseToBuilder() {
     *
     * }
     *
     *
     * public ApiResponseToBuilder setMessage(String message) { this.message =
     * message; return this; }
     *
     * public ApiResponseToBuilder setSuccess(boolean success) { this.success =
     * success; return this; }
     *
     * public ApiResponseToBuilder setHttpStatus(HttpStatus httpStatus) {
     * this.httpStatus = httpStatus; return this; }
     *
     * public ApiExceptionPayload build() { ApiExceptionPayload apiResponseTO = new
     * ApiExceptionPayload(this); return apiResponseTO; } }
     */

}
