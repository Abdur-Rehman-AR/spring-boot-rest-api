package com.example.api.rest_api_database_crud;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.http.converter.HttpMessageNotReadableException;

// Makes this class a global exception handler for all controllers.
@ControllerAdvice
public class GlobalExceptionHandler {

    // 1.

    // Exception handling, this method handles ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    // Passes the exact error object that was thrown into the method as variable e,
    // giving you access to its error message and details.
    public ResponseEntity<String> exceptionResourceNotFound(ResourceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    // 2.

    // Tells Spring that whenever a request arrives with broken JSON syntax or
    // unparseable data types, execute this method automatically.
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleBadRequest(HttpMessageNotReadableException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request data.");
    }

    // 3.

    // Exception is the parent class for almost all exceptions for both custom ones
    // you create and built-in ones like NullPointerException. It catches any error
    // that occurs in the application if a more specific exception handler hasn't
    // caught it yet.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralRequest(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong on the server.");
    }
}