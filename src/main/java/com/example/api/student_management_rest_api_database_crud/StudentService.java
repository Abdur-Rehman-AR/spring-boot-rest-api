package com.example.api.student_management_rest_api_database_crud;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.jdbc.core.JdbcTemplate;

// This is a Spring-managed bean class whose role is to conatin business logic.
@Service
public class StudentService {

    // using DI, so we can call DB operation from here
    private final StudentRepository studentRepository;

    // Also using JdbcTemplate class here through DI
    private final JdbcTemplate jdbcTemplate;

    // using the constructor DI to inject values
    public StudentService(StudentRepository studentRepository, JdbcTemplate jdbcTemplate) {
        this.studentRepository = studentRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    // Testing the JdbcTemplate by using a simple sql command
    public List<Student> getStudents() {
        String sql = "Select * From student_list";

        // We use JdbcTemplate object to communicate with the database.
        // query() method runs a SELECT query and process the rows returned by the
        // database. (row, i) means that for every row that comes back from the
        // database, execute the code after ->.
        return jdbcTemplate.query(sql, (row, i) -> {
            Student student = new Student();

            // getString() is a method of ResultSet. It means get the value from a
            // database column and return it as a Java String.
            student.setAge(row.getInt("age"));
            student.setName(row.getString("name"));

            // Each row is converted into a Student object and returned to JdbcTemplate.
            // JdbcTemplate collects all Student objects into a List<Student>.
            return student;
        });
    }

    // Creating a logging object and initializing it

    // Declared 'final' bcz we dont want that another logger object reassigned to it
    // LoggerFactory is a Spring class that creates Logger objects.
    // getLogger() actually creates a Logger. StudentService.class tells it "this
    // Logger is for the StudentService class."
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    // ****** logger class methods ******
    // info() method is used to print general operational messages about what your
    // application is doing during normal execution.
    // warn() is used when something unusual or potentially problematic happens, but
    // it is not necessarily a system error.

    // 1. INSERT

    public Student saveStudent(Student s) {
        Student student = studentRepository.save(s);
        logger.info("Student created successfully.");
        return student;
    }

    // 2. READ

    // a. Get Students from the Page

    // Page interface is return type as a output while Pageable is interface used
    // for input + filters are applied as well

    public Page<Student> getStudents(Pageable pageable, String name, Integer age) {

        Page<Student> students;

        if (name != null && age != null) {
            students = studentRepository.findByNameAndAge(name, age, pageable);
            logger.info("Student fetched using filters.");
        } else if (name != null && age == null) {
            students = studentRepository.findByName(name, pageable);
            if (!students.isEmpty()) {
                logger.info("Student(s) fetched with name {}", name);
            } else {
                logger.warn("Student not found with name {}", name);
            }
        } else if (name == null && age != null) {
            students = studentRepository.findByAge(age, pageable);
            logger.info("Student fetched using filters.");
        } else {
            students = studentRepository.findAll(pageable);
            logger.info("All students fetched.");
        }
        return students;
    }

    // b. Read all
    public List<Student> getStudents(String name, Integer age) {

        List<Student> students;

        if (name != null && age != null) {
            students = studentRepository.findByNameAndAge(name, age);
            logger.info("Student fetched using filters.");
        } else if (name != null && age == null) {
            students = studentRepository.findByName(name);
            if (!students.isEmpty()) {
                logger.info("Student(s) fetched with name {}", name);
            } else {
                logger.warn("Student not found with name {}", name);
            }
        } else if (name == null && age != null) {
            students = studentRepository.findByAge(age);
            logger.info("Student fetched using filters.");
        } else {
            students = studentRepository.findAll();
            logger.info("All students fetched.");
        }
        return students;
    }

    // c. Read by specific Id
    public Student getStudentById(Integer id) {
        Optional<Student> optional = studentRepository.findById(id);

        if (optional.isPresent()) {
            logger.info("Student fetched with id {}", id);
            return optional.get();
        } else {
            logger.warn("Student not found with id {}", id);
            // Create an exception object and immediately throw it.
            throw new ResourceNotFoundException("Student not found with ID: " + id);
        }
    }

    // 3. DELETE

    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
        logger.info("Student deleted with id {}", id);
    }
}