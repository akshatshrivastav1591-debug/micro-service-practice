package com.MicoservicePractice.PaymentService.ExceptionHandeller.CustomExceptions;

public class ConflictResourceException extends RuntimeException {
    public ConflictResourceException(String message) {
        super(message);
    }
}
