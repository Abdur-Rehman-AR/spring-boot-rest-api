package com.example.api.rest_api_database_crud;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer>{
 
    // all methods declarations bcz implementation will be provided by hiberante
    // at run time.

    // Read by name method
    List<Student> findByName(String name);

}