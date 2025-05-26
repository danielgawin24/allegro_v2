package com.example.demo.exceptions;

public class UserAlreadyRegisteredException extends RuntimeException {
    public UserAlreadyRegisteredException(String value) {
        super(value);
    }
}
