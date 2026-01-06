package com.back.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    record ErrorResponse(
            int code,
            String message
    ) {
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> handleDomainException(DomainException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.code,
                e.getMessage()
        );
        return ResponseEntity
                .status(e.code)
                .body(errorResponse);
    }
}
