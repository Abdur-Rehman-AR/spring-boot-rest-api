package com.example.api.rest_api_database_crud;

// RuntimeException is the parent class of many runtime error
public class ResourceNotFoundException extends RuntimeException {

    public void studentNotFoundException(String message) {
        // super(message) calls a constructor of RuntimeException that accepts a
        // String. As we need to store this message anywhere.
        super(message);
    }

}