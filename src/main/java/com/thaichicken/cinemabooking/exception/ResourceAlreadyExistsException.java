package com.thaichicken.cinemabooking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class ResourceAlreadyExistsException extends RuntimeException {
    public ResourceAlreadyExistsException(String message) {
        super(message);
    }

    public ResourceAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
