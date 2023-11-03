package org.example.HW20.exceptions.service;

public class ServiceExistException extends RuntimeException {
    public ServiceExistException(String message) {
        super(message);
    }
}
