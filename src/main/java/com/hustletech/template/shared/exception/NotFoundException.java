package com.hustletech.template.shared.exception;

import org.springframework.http.HttpStatus;

/**
 * Custom exception class for handling not-found errors within the application.
 * Extends ApiException to include specific handling for HTTP 404 Not Found
 * status.
 */
public class NotFoundException extends ApiException {

    /**
     * Constructs a NotFoundException with a custom message.
     *
     * @param message the custom message describing the not-found error
     */
    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

    /**
     * Constructs a NotFoundException with a predefined message format.
     * This constructor is typically used when an entity is not found by its
     * message.
     *
     * @param entity  the name of the entity
     * @param message the message of the entity that was not found
     */
    public NotFoundException(String entity, Object message) {
        super(String.format("No %s with ID %s was found", entity, message), HttpStatus.NOT_FOUND);
    }

    /**
     * Constructs a NotFoundException with a custom message.
     * This constructor is typically used when an something is not found.
     *
     * @param message the message of the entity that was not found
     */
    public NotFoundException(Object message) {
        super(String.format("%s", message), HttpStatus.NOT_FOUND);
    }

}
