package com.example.userService.exception;

import org.springframework.http.HttpStatus;

public class ConflictRequestException extends ApiException {
    public ConflictRequestException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
