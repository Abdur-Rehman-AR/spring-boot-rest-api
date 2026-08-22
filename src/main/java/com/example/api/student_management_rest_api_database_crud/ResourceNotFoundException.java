package com.example.api.student_management_rest_api_database_crud;

// Creating a custom Exception as we want to give a specific meaning to a problem.
// RuntimeException is the parent class of many unchecked exceptions.
public class ResourceNotFoundException extends RuntimeException {

    // Constructor of ResourceNotFoundException
    public ResourceNotFoundException(String message) {
        // super(message) calls a constructor of RuntimeException that accepts a
        // String. As we need to store this message anywhere.
        super(message);
    }

}