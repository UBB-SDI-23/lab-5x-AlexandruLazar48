package com.example.labmpp.exception;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException (String message) {
        super(message);
    }
}
