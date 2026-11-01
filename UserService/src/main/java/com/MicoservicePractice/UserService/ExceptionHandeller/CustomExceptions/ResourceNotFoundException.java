package com.MicoservicePractice.UserService.ExceptionHandeller.CustomExceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
