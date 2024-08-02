package com.hustletech.template.shared.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Provides centralized exception handling across all controller methods.
 * This class captures exceptions and formats them into uniform HTTP responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Constructs a generic response entity for error handling.
     * Formats a structured response including the HTTP status code, error reason,
     * and a detailed message or map of errors.
     *
     * @param message The error message or a map of field errors
     * @param status  The HTTP status to be returned
     * @return A ResponseEntity containing the structured error response
     */
    private ResponseEntity<Map<String, Object>> buildErrorResponse(Object message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", status.value());
        response.put("error", status.getReasonPhrase());
        response.put("message", message);
        return new ResponseEntity<>(response, status);
    }

    // Exception handlers

    /**
     * Handles API exceptions and constructs an appropriate HTTP response.
     * HTTP 500 - Internal Server Error: A generic error message, given when an
     * unexpected condition was encountered.
     *
     * @param ex The ApiException encountered
     * @return A ResponseEntity containing the error details and HTTP status
     */
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Map<String, Object>> handleApiException(ApiException ex) {
        return buildErrorResponse(ex.getMessage(), ex.getStatus());
    }

    /**
     * Handles BadRequestException specifically crafted for cases where multiple
     * validation errors need to be communicated.
     * HTTP 400 - Bad Request: The server cannot or will not process the request due
     * to something that is perceived to be a client error.
     *
     * @param ex The BadRequestException with detailed error messages
     * @return A ResponseEntity containing the detailed list of errors and HTTP
     *         status
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String, Object>> handleBadRequestException(BadRequestException ex) {
        return buildErrorResponse(ex.getErrors(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles NotFoundException indicating that the requested resource was not
     * found.
     * HTTP 404 - Not Found: The requested resource could not be found but may be
     * available in the future.
     *
     * @param ex The NotFoundException encountered
     * @return A ResponseEntity containing the error details and HTTP status
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFoundException(NotFoundException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles NotAuthorizedException indicating that the user is not authorized to
     * access the requested resource.
     * HTTP 403 - Forbidden: The server understood the request, but refuses to
     * authorize it.
     *
     * @param ex The NotAuthorizedException encountered
     * @return A ResponseEntity containing the error details and HTTP status
     */
    @ExceptionHandler(NotAuthorizedException.class)
    public ResponseEntity<Map<String, Object>> handleNotAuthorizedException(NotAuthorizedException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    /**
     * Handles InvalidTokenException indicating that the provided token is invalid.
     * HTTP 401 - Unauthorized: The request requires user authentication.
     *
     * @param ex The InvalidTokenException encountered
     * @return A ResponseEntity containing the error details and HTTP status
     */
    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidTokenException(InvalidTokenException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles InvalidCredentialsException indicating that the username/password
     * combination is incorrect.
     * HTTP 401 - Unauthorized: The request requires user authentication.
     *
     * @param ex The InvalidCredentialsException encountered
     * @return A ResponseEntity containing the error details and HTTP status
     */
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
