package com.github.GaskaPiotr.spring_boot_boilerplate.exception;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
