package com.example.api.rest_api_database_crud;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class StudentService {

    // using DI, so we can call DB operation from here

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }

    // 1. INSERT

    private Student createStudent(Student s) {
        Student student = studentRepository.save(s);
        return student;
    }

    // 2. READ

    // a. Read all
    private List<Student> getStudents() {
        List<Student> student = studentRepository.findAll();
        return student;
    }

    // b. Read by specific Id
    private Student getStudentById(int id) {
        Optional<Student> optional = studentRepository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }

    // c. Read by specific Name
    private List<Student> getStudentsByName(String name) {
        List<Student> student = studentRepository.findByName(name);
        return student;
    }

    // 3. UPDATE

    private Student updateStudent(int id, String name, int age) {
        Optional<Student> optional = studentRepository.findById(id);

        if (optional.isPresent()) {
            Student student = optional.get();
            student.setName(name);
            student.setAge(age);
            student = studentRepository.save(student);
            return student;
        } else {
            return null;
        }
    }

    // 4. DELETE

    private boolean deleteStudent(int id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}