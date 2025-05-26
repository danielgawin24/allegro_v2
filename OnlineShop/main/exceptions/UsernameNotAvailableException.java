package com.example.demo.exceptions;

public class UsernameNotAvailableException extends RuntimeException {
    public UsernameNotAvailableException(String value) {
        super(value);
    }
}
