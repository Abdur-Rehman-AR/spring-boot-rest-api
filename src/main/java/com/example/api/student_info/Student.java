package com.example.api.student_info;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

// this annotation defines how to order key-values in the API Json response
@JsonPropertyOrder({ "age", "name" })
public class Student {

    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // as variable of this class are private so by default jackson work with
    // getter methods to make key-value pair in json format
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}