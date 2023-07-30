package com.example.lomboku.controller;

public class KukuException extends RuntimeException{
    public KukuException() {
    }

    public KukuException(String message) {
        super(message);
    }

    public KukuException(String message, Throwable cause) {
        super(message, cause);
    }

    public KukuException(Throwable cause) {
        super(cause);
    }

    public KukuException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
