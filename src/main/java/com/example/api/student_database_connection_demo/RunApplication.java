package com.example.api.student_database_connection_demo;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RunApplication {
    public static void main(String[] args) {

        SpringApplication.run(RunApplication.class, args);
    }

    // CommandLineRunner, an interface used to run its method only once after the
    // application starts and also it is using DI to create a object.
    @Bean
    public CommandLineRunner run(StudentRepository repo) {
        return args -> {

            Student student = new Student();
            student.setName("Abdur Rehman");
            student.setAge(21);

            // 1. INSERT DATA
            repo.save(student);

            // 2. READ DATA
            List<Student> students = repo.findAll();
            System.out.println("All Student data:");
            for (Student s : students) {
                System.out.println("ID: " + s.getId() + " | Name: " + s.getName() + " | Age: " + s.getAge());
            }

            // 2.1 READ DATA WITH SPECIFIC ID

            // The part after "findBy" must exactly match the field name in your entity.
            // we are using "optional" datatype bcz it might happen that student with id
            // 100 is not present.

            Optional<Student> std = repo.findById(2);

            // isPresent() tells "Is there a Student inside the box?" and get() tells
            // "Give me the Student from the box."

            if (std.isPresent()) {
                Student s = std.get();
                System.out.println("Student with Specific ID:");
                System.out.println("ID: " + s.getId() + " | Name: " + s.getName() + " | Age: " + s.getAge());
            } else {
                System.out.println("Student not present");
            }

            // 2.2 READ DATA WITH SPECIFIC NAME

            List<Student> std2 = repo.findByName("Abdur Rehman");
            System.out.println("Student with specific name:");
            for (Student s : std2) {
                System.out.println("ID: " + s.getId() + " | Name: " + s.getName() + " | Age: " + s.getAge());
            }

            // UPDATE DATA

            Optional<Student> optional = repo.findById(3);
            if (optional.isPresent()) {
                Student student2 = optional.get();
                student2.setName("Ali Gul");
                student2.setAge(22);
                repo.save(student2);
            } else {
                System.out.println("Student not found");
            }

            // DELETE DATA

            if (repo.existsById(8)) {
                repo.deleteById(8);
            } else {
                System.out.println("Student not found");
            }
        };
    }
}