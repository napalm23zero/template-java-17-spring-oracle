package com.hustletech.template.shared.exception;

import org.springframework.http.HttpStatus;

/**
 * Custom exception class for handling invalid token errors.
 * Extends ApiException to include specific handling for HTTP 401 Unauthorized
 * status.
 */
public class InvalidTokenException extends ApiException {

    /**
     * Constructs an InvalidTokenException with a custom message.
     *
     * @param message the custom message describing the token error
     */
    public InvalidTokenException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Constructs an InvalidTokenException with a default message.
     * This constructor is typically used when the provided token is invalid.
     */
    public InvalidTokenException() {
        super("The provided token is invalid.", HttpStatus.UNAUTHORIZED);
    }
}
