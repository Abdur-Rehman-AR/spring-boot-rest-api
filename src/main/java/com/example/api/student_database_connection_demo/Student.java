package com.example.api.student_database_connection_demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// @Table is used to give a customize name to the table instead of the current class name
@Table(name = "Student_Info")
// @Entity tells Hibernate that this class should become a table in the
// database.
@Entity
public class Student {

    // @Id marks the field as the Primary Key of the database table.
    @Id
    // Lets the database automatically generate the primary key value.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // @Column allows customization in the column

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    /*
     * When Spring Boot reads data out of MySQL, it creates a new Student object. To
     * do this, Hibernate requires a empty constructor so it can instantiate the
     * class before filling in the values.
     */
    public Student() {

    }

    // Constructor to initialize a Student object.

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}