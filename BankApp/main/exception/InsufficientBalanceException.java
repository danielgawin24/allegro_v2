package com.example.bankApp.exception;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String value) {
        super(value);
    }
}
