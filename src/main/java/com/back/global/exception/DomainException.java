package com.back.global.exception;

public class DomainException extends RuntimeException {
    int code;

    public DomainException(int code, String message) {
        super(message);
        this.code = code;
    }
}
