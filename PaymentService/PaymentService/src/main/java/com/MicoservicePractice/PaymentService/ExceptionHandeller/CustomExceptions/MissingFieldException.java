package com.MicoservicePractice.PaymentService.ExceptionHandeller.CustomExceptions;

public class MissingFieldException extends RuntimeException {
    public MissingFieldException(String message) {
        super(message);
    }
}
