package com.example.api.rest_api_database_crud;

public class StudentController {
 
    // using DI here, so we can call service class methods from here
    private StudentService studentService;
    public StudentController(StudentService studentService)
    {
        this.studentService = studentService;
    }
}