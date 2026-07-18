package com.example.api.student_list;

public class Student {

    private String name;
    private Integer id;

    // Jackson needs a no-argument constructor when creating objects from JSON.
    public Student() {

    }

    public Student(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }
}