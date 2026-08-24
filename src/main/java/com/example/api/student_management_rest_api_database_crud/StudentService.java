package com.example.api.student_management_rest_api_database_crud;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

// This is a Spring-managed bean class whose role is to conatin business logic.
@Service
public class StudentService {

    // using DI, so we can call DB operation from here

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // 1. INSERT

    public Student saveStudent(Student s) {
        Student student = studentRepository.save(s);
        return student;
    }

    // 2. READ

    // a. Get Students from the Page
    // Page interface is return type as a output while Pageable is interface used
    // for input + filters are applied as well

    public Page<Student> getStudents(Pageable pageable, String name, Integer age) {
        if (name != null && age != null) {
            return studentRepository.findByNameAndAge(name, age, pageable);
        } else if (name != null && age == null) {
            return studentRepository.findByName(name, pageable);
        } else if (name == null && age != null) {
            return studentRepository.findByAge(age, pageable);
        } else {
            return studentRepository.findAll(pageable);
        }
    }

    // b. Read all
    public List<Student> getStudents(String name, Integer age) {

        if (name != null && age != null) {
            return studentRepository.findByNameAndAge(name, age);
        } else if (name != null && age == null) {
            return studentRepository.findByName(name);
        } else if (name == null && age != null) {
            return studentRepository.findByAge(age);
        } else {
            return studentRepository.findAll();
        }
    }

    // c. Read by specific Id
    public Student getStudentById(Integer id) {
        Optional<Student> optional = studentRepository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        } else {
            // Create an exception object and immediately throw it.
            throw new ResourceNotFoundException("Student not found with ID: " + id);
        }
    }

    // d. Read by specific Name
    public List<Student> getStudentsByName(String name) {
        List<Student> student = studentRepository.findByName(name);
        return student;
    }

    // 3. DELETE

    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }
}