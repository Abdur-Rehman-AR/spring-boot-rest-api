package com.example.api.student_management_rest_api_database_crud;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;

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

    // Exception is the parent class of many Java exceptions.
    // This handler catches exceptions that don't have a more specific handler.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralRequest(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong on the server.");
    }

    // 4.

    // Exception that will happen when client has given the arguments for the
    // Student fields that are not passing the validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationError(MethodArgumentNotValidException e) {

        // e.getBindingResult() fetches the meta info about the error causing fields
        // getFieldErrors() fetches the list containing all field errors.
        // getDefaultMessage() takes out the default message present in the field's
        // validation annotation
        String msg = "";
        List<FieldError> list = e.getBindingResult().getFieldErrors();
        for (FieldError fieldError : list) {
            msg = msg.concat(fieldError.getDefaultMessage()).concat(" ");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
    }
}