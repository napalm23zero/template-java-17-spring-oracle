package com.hustletech.template.shared.exception;

import org.springframework.http.HttpStatus;

/**
 * Custom exception class for handling authorization errors within the
 * application.
 * Extends ApiException to include specific handling for HTTP 403 Forbidden
 * status.
 */
public class NotAuthorizedException extends ApiException {

    /**
     * Constructs a NotAuthorizedException with a custom message.
     *
     * @param message the custom message describing the authorization error
     */
    public NotAuthorizedException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }

    /**
     * Constructs a NotAuthorizedException with a default message.
     * This constructor is typically used when access to a resource is forbidden.
     */
    public NotAuthorizedException() {
        super("You are not authorized to access this resource.", HttpStatus.FORBIDDEN);
    }
}
