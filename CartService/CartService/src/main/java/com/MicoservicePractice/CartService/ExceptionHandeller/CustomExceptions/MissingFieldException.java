package com.MicoservicePractice.CartService.ExceptionHandeller.CustomExceptions;

public class MissingFieldException extends RuntimeException {
    public MissingFieldException(String message) {
        super(message);
    }
}
