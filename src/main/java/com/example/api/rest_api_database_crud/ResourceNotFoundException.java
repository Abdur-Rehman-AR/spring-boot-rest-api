package com.example.api.rest_api_database_crud;

// Creating a custom Exception as we want to give a specific meaning to a problem.
// RuntimeException is the parent class of many runtime error
public class ResourceNotFoundException extends RuntimeException {

    // Calling this class's constructor
    public ResourceNotFoundException(String message) {
        // super(message) calls a constructor of RuntimeException that accepts a
        // String. As we need to store this message anywhere.
        super(message);
    }

}