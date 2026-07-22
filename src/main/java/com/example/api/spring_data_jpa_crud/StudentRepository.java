package com.example.api.spring_data_jpa_crud;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

// Repository interface used to perform database operations on Student entities
public interface StudentRepository extends JpaRepository<Student, Integer> {

    // custom method to read student with specific name
    public List<Student> findByName(String name);
}