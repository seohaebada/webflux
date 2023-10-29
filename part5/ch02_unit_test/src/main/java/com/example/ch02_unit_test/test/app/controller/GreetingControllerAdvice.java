package com.example.ch02_unit_test.test.app.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GreetingControllerAdvice {
    @ExceptionHandler
    public String handleGreetingException(GreetingException e) {
        return "GreetingException";
    }
}
