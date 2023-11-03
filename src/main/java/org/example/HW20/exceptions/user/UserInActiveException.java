package org.example.HW20.exceptions.user;

public class UserInActiveException extends RuntimeException {
    public UserInActiveException(String message) {
        super(message);
    }
}
