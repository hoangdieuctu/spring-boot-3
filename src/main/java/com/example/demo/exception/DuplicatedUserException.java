package com.example.demo.exception;

public class DuplicatedUserException extends ServiceException {

    public DuplicatedUserException(String username) {
        super(String.format("User '%s' already existed", username));
    }
}
