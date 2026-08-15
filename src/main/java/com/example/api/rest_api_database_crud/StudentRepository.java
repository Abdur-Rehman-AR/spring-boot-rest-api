package com.example.api.rest_api_database_crud;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    // Methods are only declared here.
    // Spring Data JPA provides their implementation at runtime.
    // Read by name method
    List<Student> findByName(String name);

}