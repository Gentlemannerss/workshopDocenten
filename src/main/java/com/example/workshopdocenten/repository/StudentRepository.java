package com.example.workshopdocenten.repository;

import com.example.workshopdocenten.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
