package com.example.api.student_list;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import org.springframework.http.ResponseEntity;

// Controller class handles the http requests and responses

@RestController
public class StudentController {

    // using DI to inject student class object in current class

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // 1. GET Method to retrieve all Students

    @GetMapping("/students")
    public ResponseEntity<ArrayList<Student>> studentList() {
        return ResponseEntity.status(200).body(studentService.getAllStudents());
    }

    // 2. GET Method to retrieve Student with a specific ID
    // value inside id (e.g, 5) is Path Parameter, used to access a specific student

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {

        Student std = studentService.getStudentById(id);

        // build() method will return an empty ResponseEntity object with a 404
        // Not Found status code and no body data.

        if (std == null)
            return ResponseEntity.status(404).build();
        else
            return ResponseEntity.status(200).body(std);
    }

    // 3. GET Method to filter the resource's data

    @GetMapping("/students/search")
    public ResponseEntity<?> getStudentsByName(@RequestParam String name) {
        ArrayList<Student> list = studentService.getStudentByName(name);
        if (list.isEmpty()) {
            return ResponseEntity.status(404).body("Students with name " + name + " not present in the list.");
        } else {
            return ResponseEntity.status(200).body(list);
        }
    }

    // 4. POST Method to create a student

    @PostMapping("/students")
    // @RequestBody will take the JSON from the request body and convert it into a
    // java object.
    public ResponseEntity<Student> createStudent(@RequestBody Student newStudent) {
        ArrayList<Student> list = studentService.getAllStudents();
        list.add(newStudent);
        return ResponseEntity.status(201).body(newStudent);
    }

    // 5. PUT Method to update a Student's record

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Integer id, @RequestBody Student updateStudent) {
        // first we will check either student exists or not

        Student std = studentService.getStudentById(id);

        if (std == null) {
            return ResponseEntity.status(404).build();
        } else {
            std.setName(updateStudent.getName());
            std.setId(updateStudent.getId());
        }
        return ResponseEntity.status(200).body(std);
    }

    // 6. PATCH Method to update the specific fields of an object

    @PatchMapping("/students/{id}")
    public ResponseEntity<Student> patchStudent(@PathVariable Integer id, @RequestBody Student newStudent) {
        // first we will check either student exists or not

        Student std = studentService.getStudentById(id);

        if (std == null) {
            return ResponseEntity.status(404).build();
        } else {
            if (newStudent.getName() != null) {
                std.setName(newStudent.getName());
            }
            if (newStudent.getId() != null) {
                std.setId(newStudent.getId());
            }
            return ResponseEntity.status(200).body(std);
        }
    }

    // 7. Delete the student with specific id

    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Integer id) {
        Student std = studentService.getStudentById(id);

        if (std == null) {
            return ResponseEntity.status(404).body("Student could not be deleted because it does not exist.");
        } else {
            boolean result = studentService.deleteStudent(std);
            if (result)
                return ResponseEntity.status(200).body("Student got deleted.");
            else
                return ResponseEntity.status(500).body("An internal Server error has occured.");
        }
    }
}