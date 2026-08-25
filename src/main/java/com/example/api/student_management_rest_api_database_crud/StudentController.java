package com.example.api.student_management_rest_api_database_crud;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import jakarta.validation.Valid;

// tells spring that this class will handle the http requests and responses
@RestController
public class StudentController {

    // using DI here, so we can call service class methods from here
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // 1. GET Method to Read Students

    // a. Get all students or only get students by specific fields conditions
    @GetMapping("/api/v1/students")

    // ResponseEntity is a Spring class that lets your Controller control the
    // response body, HTTP status code and headers sent back to the client.
    // Here, request parameter is not neccessary.
    public ResponseEntity<List<Student>> getStudents(@RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age) {
        return ResponseEntity.ok(studentService.getStudents(name, age));
    }

    // b. Get student by id
    @GetMapping("/api/v1/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    // c. Getting students from the page
    @GetMapping("/api/v1/students/page")
    public ResponseEntity<Page<Student>> getStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age) {

        // Creating the instruction for sorting

        // creating a sort object and pass it to service class method
        // Sort.unsorted() means that don't apply any sorting.
        Sort sorting = Sort.unsorted();

        if (sort != null) {
            String[] parts = sort.split(",");
            if (parts.length == 2 && parts[1].equalsIgnoreCase("desc")) {
                sorting = Sort.by(parts[0]).descending();
            } else {
                sorting = Sort.by(parts[0]).ascending();
            }
        }

        // PageRequest.of() creates the pagination instructions and creates the object
        Pageable pageable = PageRequest.of(page, size, sorting);
        return ResponseEntity.ok(studentService.getStudents(pageable, name, age));
    }

    // 2. POST method to create student

    @PostMapping("/api/v1/students")
    // @RequestBody tells Spring to take data from HTTP request body and
    // convert it into a Java object. @Valid tells Spring boot to Check this
    // object's validation rules before calling my controller method.
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student s) {
        Student student = studentService.saveStudent(s);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    // 3. PUT method to update the Student's all field

    @PutMapping("/api/v1/students/{id}")
    public ResponseEntity<?> fullUpdateStudent(@PathVariable Integer id, @Valid @RequestBody Student s) {

        // Checking either student exists or not
        Student student = studentService.getStudentById(id);
        student.setName(s.getName());
        student.setAge(s.getAge());
        studentService.saveStudent(student);
        return ResponseEntity.ok(student);
    }

    // 4. PATCH method for partial update

    // we are not giving @Valid here bcz if we intentionly omitted any field this
    // annotation will cause an unneccsary error.
    @PatchMapping("/api/v1/students/{id}")
    public ResponseEntity<?> patchUpdateStudent(@PathVariable Integer id, @RequestBody Student s) {
        // Checking either student exists or not
        Student student = studentService.getStudentById(id);
        if (s.getName() != null)
            student.setName(s.getName());
        if (s.getAge() != null)
            student.setAge(s.getAge());
        studentService.saveStudent(student);
        return ResponseEntity.ok(student);
    }

    // 5. DELETE student

    @DeleteMapping("/api/v1/students/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Integer id) {
        // Checking either student exists or not
        studentService.getStudentById(id);
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    // ----------------- JdbcTemplate methods endpoints -----------------

    // 1. Read

    @GetMapping("/api/v2/students")
    public ResponseEntity<List<Student>> getStudents()
    {
        return ResponseEntity.ok(studentService.readStudents());
    }

    
}