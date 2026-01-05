package com.back.global.exception;

public class DomainException extends RuntimeException {
    int resultCode;

    public DomainException(int resultCode, String message) {
        super(message);
        this.resultCode = resultCode;
    }
}
