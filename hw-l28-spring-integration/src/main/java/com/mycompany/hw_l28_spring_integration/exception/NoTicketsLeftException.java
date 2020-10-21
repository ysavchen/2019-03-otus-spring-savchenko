package com.mycompany.hw_l28_spring_integration.exception;

public class NoTicketsLeftException extends RuntimeException {

    public NoTicketsLeftException(String message) {
        super(message);
    }
}
