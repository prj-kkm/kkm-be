package com.example.kkm.user.auth.exception;

public class PasswordNotMatchException extends RuntimeException {

    public PasswordNotMatchException(String message) {
        super(message);
    }
}
