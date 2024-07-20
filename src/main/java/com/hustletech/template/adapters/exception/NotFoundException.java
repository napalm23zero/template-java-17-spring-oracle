package com.hustletech.template.adapters.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiException {
    // Constructor for custom message
    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

    // Constructor for entity-based message
    public NotFoundException(String entity, Object identifier) {
        super(String.format("%s with ID %s not found", entity, identifier), HttpStatus.NOT_FOUND);
    }
}
