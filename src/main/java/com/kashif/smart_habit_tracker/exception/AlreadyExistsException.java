package com.kashif.smart_habit_tracker.exception;

import org.springframework.http.HttpStatus;

public class AlreadyExistsException extends RuntimeException{

    private HttpStatus status;

    public AlreadyExistsException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
