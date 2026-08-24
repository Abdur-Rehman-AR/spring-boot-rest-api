package com.example.api.student_management_rest_api_database_crud;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    // Methods are only declared here.
    // Spring Data JPA provides their implementation at runtime.

    // Read methods for normal Student List.
    List<Student> findByName(String name);

    List<Student> findByNameAndAge(String name, Integer age);

    List<Student> findByAge(Integer age);

    // Read methods for Paged Student List.
    Page<Student> findByName(String name, Pageable pageable);

    Page<Student> findByNameAndAge(String name, Integer age, Pageable pageable);

    Page<Student> findByAge(Integer age, Pageable pageable);

}