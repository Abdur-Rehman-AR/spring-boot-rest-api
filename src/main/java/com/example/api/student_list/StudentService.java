package com.example.api.student_list;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.stereotype.Component;

// Service class contain the business logic

@Component
public class StudentService {

    // useful variables
    private ArrayList<Student> list = new ArrayList<>();

    // list of students to return
    public ArrayList<Student> getAllStudents() {
        return list;
    }

    // we are using object's equal method because getId/Name can return null as well

    // Method to get student by id field
    public Student getStudentById(Integer id) {
        for (Student student : list) {
            if (Objects.equals(student.getId(), id)) {
                return student;
            }
        }
        return null;
    }

    // Method to get student by name field
    public ArrayList<Student> getStudentByName(String name) {
        ArrayList<Student> matchedStudents = new ArrayList<>();
        for (Student student : list) {
            if (Objects.equals(student.getName(), name)) {
                matchedStudents.add(student);
            }
        }
        return matchedStudents;
    }

    // method to remove student from list
    public boolean deleteStudent(Student student) {
        return list.remove(student);
    }
}