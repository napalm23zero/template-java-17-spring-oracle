package com.hustletech.template.adapters.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
    private final HttpStatus status;
    private final String message;

    public ApiException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }
}
