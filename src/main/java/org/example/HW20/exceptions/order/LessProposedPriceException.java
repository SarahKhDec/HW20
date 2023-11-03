package org.example.HW20.exceptions.order;

public class LessProposedPriceException extends RuntimeException {
    public LessProposedPriceException(String message) {
        super(message);
    }
}
