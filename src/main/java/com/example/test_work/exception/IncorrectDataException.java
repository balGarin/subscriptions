package com.example.test_work.exception;

public class IncorrectDataException extends RuntimeException {
    public IncorrectDataException(String message) {
        super(message);
    }
}
