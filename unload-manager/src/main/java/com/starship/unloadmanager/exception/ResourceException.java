package com.starship.unloadmanager.exception;

import org.springframework.http.HttpStatus;

public class ResourceException extends RuntimeException {
    private final HttpStatus httpStatus;

    public ResourceException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public ResourceException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
