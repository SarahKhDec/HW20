package org.example.HW20.exceptions.user;

public class LessCreditException extends RuntimeException {
    public LessCreditException(String message) {
        super(message);
    }
}
