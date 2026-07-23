package com.example.api.rest_api_database_crud;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Table(name = "student_list") // to give table a customize name instead of class name
@Entity // Tells Hibernate that this class should be mapped to a database table.
public class Student {

    @Id // marks the id field as Primary key of the table
    // let database automatically generate the value for this column below
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // @Column allows customization in the specified columns

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    // Hibernate uses this constructor to create Student objects when reading data
    // from the database.

    public Student() {

    }

    // Actual constructor to store values in an object

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    // getters and setters

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