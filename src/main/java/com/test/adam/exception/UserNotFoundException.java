package com.test.adam.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super("GitHub user '%s' not found".formatted(username));
    }
}
