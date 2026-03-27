package com.kashif.smart_habit_tracker.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends RuntimeException{

    private HttpStatus status;

    public UserAlreadyExistsException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
