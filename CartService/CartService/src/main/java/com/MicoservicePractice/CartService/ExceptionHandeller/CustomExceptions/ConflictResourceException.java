package com.MicoservicePractice.CartService.ExceptionHandeller.CustomExceptions;

public class ConflictResourceException extends RuntimeException {
    public ConflictResourceException(String message) {
        super(message);
    }
}
