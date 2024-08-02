package com.hustletech.template.shared.exception;

import org.springframework.http.HttpStatus;

/**
 * Custom exception class for handling invalid credentials errors.
 * Extends ApiException to include specific handling for HTTP 401 Unauthorized
 * status.
 */
public class InvalidCredentialsException extends ApiException {

    /**
     * Constructs an InvalidCredentialsException with a custom message.
     *
     * @param message the custom message describing the credential error
     */
    public InvalidCredentialsException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Constructs an InvalidCredentialsException with a default message.
     * This constructor is typically used when the username/password combination is
     * incorrect.
     */
    public InvalidCredentialsException() {
        super("The username/password combination is incorrect.", HttpStatus.UNAUTHORIZED);
    }
}
