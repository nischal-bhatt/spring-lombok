package com.example.lomboku.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_GATEWAY)
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
