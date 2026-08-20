package com.example.api.rest_api_database_crud;

import java.util.List;
import java.util.Optional;

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

    // a. Read all
    public List<Student> getStudents() {
        List<Student> student = studentRepository.findAll();
        return student;
    }

    // b. Read by specific Id
    public Student getStudentById(Integer id) {
        Optional<Student> optional = studentRepository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        } else {
            // Create an exception object and immediately throw it.
            throw new ResourceNotFoundException("Student not found with ID: " +id);
        }
    }

    // c. Read by specific Name
    public List<Student> getStudentsByName(String name) {
        List<Student> student = studentRepository.findByName(name);
        return student;
    }

    // 3. UPDATE

    public Student updateStudent(Integer id, String name, int age) {
        Optional<Student> optional = studentRepository.findById(id);

        if (optional.isPresent()) {
            Student student = optional.get();
            student.setName(name);
            student.setAge(age);
            studentRepository.save(student);
            return student;
        } else {
            return null;
        }
    }

    // 4. DELETE

    public boolean deleteStudent(Integer id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}