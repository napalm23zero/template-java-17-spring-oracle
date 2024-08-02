package com.hustletech.template.shared.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

/**
 * Custom exception class that encapsulates error details for API responses.
 * This class extends RuntimeException and includes HTTP status information,
 * making it suitable for use in web contexts where HTTP responses are required.
 *
 * Attributes:
 * - status: HTTP status code associated with the exception.
 * - message: Error message describing the exception.
 */
@Getter
public class ApiException extends RuntimeException {
    private final HttpStatus status;
    private final String message;

    /**
     * Constructs a new ApiException with a specified message and HTTP status.
     *
     * @param message The error message associated with the exception.
     * @param status  The HTTP status code that should be returned.
     */
    public ApiException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }
}
