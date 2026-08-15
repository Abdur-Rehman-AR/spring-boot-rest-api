package com.example.api.rest_api_database_crud;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// tells spring that this class will handle the http requests and responses
@RestController
public class StudentController {

    // using DI here, so we can call service class methods from here
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // 1. GET Method to Read Students

    // a. Get all students
    @GetMapping("/students")

    // ResponseEntity is a Spring class that lets your Controller control the
    // response body, HTTP status code and headers sent back to the client.
    public ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.ok(studentService.getStudents());
    }

    // b. Get student by id
    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            // build() method will return an empty ResponseEntity object with a 404
            // Not Found status code and no body data.
            return ResponseEntity.status(404).build();
        }
    }

    // c. Get student by name
    @GetMapping("/students/search")
    public ResponseEntity<?> getStudentByName(@RequestParam String name) {
        List<Student> student = studentService.getStudentsByName(name);

        if (student.isEmpty()) {
            return ResponseEntity.status(404).body("Student(s) with this name not found.");
        } else {
            return ResponseEntity.ok(student);
        }
    }

    // 2. POST method to create student

    @PostMapping("/students")
    // @RequestBody tells Spring to take the data from the HTTP request body and
    // convert it into a Java object.
    public ResponseEntity<Student> createStudent(@RequestBody Student s) {
        Student student = studentService.saveStudent(s);
        return ResponseEntity.status(201).body(student);
    }

    // 3. PUT method to update the Student's all field

    @PutMapping("/students/{id}")
    public ResponseEntity<?> fullUpdateStudent(@PathVariable Integer id, @RequestBody Student s) {

        // Checking either student exists or not
        Student student = studentService.getStudentById(id);
        if (student == null) {
            return ResponseEntity.status(404).body("Student not found with this id");
        } else {
            student.setName(s.getName());
            student.setAge(s.getAge());
            studentService.saveStudent(student);
            return ResponseEntity.ok(student);
        }
    }

    // 4. PATCH method for partial update

    @PatchMapping("/students/{id}")
    public ResponseEntity<?> patchUpdateStudent(@PathVariable Integer id, @RequestBody Student s) {
        // Checking either student exists or not
        Student student = studentService.getStudentById(id);
        if (student == null) {
            return ResponseEntity.status(404).body("Student not found with this id");
        } else {
            if (s.getName() != null)
                student.setName(s.getName());
            if (s.getAge() != null)
                student.setAge(s.getAge());
            studentService.saveStudent(student);
            return ResponseEntity.ok(student);
        }
    }

    // 5. DELETE student

    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Integer id) {
        // Checking either student exists or not
        Student student = studentService.getStudentById(id);
        if (student == null) {
            return ResponseEntity.status(404).body("Student not found with this id");
        } else {
            studentService.deleteStudent(id);
            return ResponseEntity.noContent().build();
        }
    }
}