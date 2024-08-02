package com.hustletech.template.shared.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

/**
 * Custom exception class for handling HTTP 400 Bad Request errors.
 * This exception supports aggregating multiple error messages into a single
 * exception instance.
 */
public class BadRequestException extends ApiException {

    private final List<String> errors;

    /**
     * Constructs a new BadRequestException with multiple error messages.
     *
     * @param errors a list of error messages associated with the bad request
     */
    public BadRequestException(List<String> errors) {
        super("Validation errors occurred. See details.", HttpStatus.BAD_REQUEST);
        this.errors = errors;
    }

    /**
     * Retrieves the list of error messages associated with this exception.
     *
     * @return a list of error messages
     */
    public List<String> getErrors() {
        return errors;
    }
}
