package com.example.api.student_info;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController // tell spring boot that this class will contain rest api endpoints
public class ApiController {

    @GetMapping("/student-info") // Annotation that maps a HTTP GET request to a specific method
    public Student StudentInfo() {

        // We create a new Student object right here
        Student s = new Student("Abdur Rehman", 21);

        // We return the Java object directly
        return s;
    }
}