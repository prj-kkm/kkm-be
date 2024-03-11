package com.example.kkm.user.auth.exception;

public class ExistsEmailException extends RuntimeException {

    public ExistsEmailException(String message) {
        super(message);
    }
}
