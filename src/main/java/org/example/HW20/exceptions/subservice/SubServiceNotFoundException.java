package org.example.HW20.exceptions.subservice;

public class SubServiceNotFoundException extends RuntimeException {
    public SubServiceNotFoundException(String message) {
        super(message);
    }
}
