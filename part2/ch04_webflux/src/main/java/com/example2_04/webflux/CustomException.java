package com.example2_04.webflux;

public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
