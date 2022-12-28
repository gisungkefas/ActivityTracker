package com.kefas.ActivityTracker.exception;

public class UsersAlreadyExistException extends RuntimeException{
    public UsersAlreadyExistException(String message) {
        super(message);
    }
}
