package com.example.workshopdocenten.repository;

import com.example.workshopdocenten.model.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {

    Iterable<Teacher> findByDobBefore(LocalDate date);

    //Bereid hier de verdere zoek mogelijkheden uit.
}
