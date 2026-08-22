package com.example.api.student_management_rest_api_database_crud;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Table(name = "student_list") // to give table a customize name instead of class name
@Entity // Tells Hibernate that this class should be mapped to a database table.
public class Student {

    @Id // marks the id field as Primary key of the table
    // let database automatically generate the value for this column below
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // @Column allows customization in the specified columns
    // @NotBlank means name cannot be null, empty, or only whitespace.
    // white spaces. @Size checks the length of input from client
    @Column(nullable = false)
    @NotBlank(message = "Name Cannot be Empty.")
    @Size(min = 3, max = 100, message = "Name should contain minimum 3 and maximum 100 characters.")
    private String name;

    // @NotNull confirms that the value will not be null by user
    @Column(nullable = false)
    @NotNull(message = "Age cannot be null.")
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