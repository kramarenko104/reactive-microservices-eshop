package com.gmail.kramarenko104.userservice.exceptions;

public class OrderNotFoundException extends RuntimeException {

    private String message;

    public OrderNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
