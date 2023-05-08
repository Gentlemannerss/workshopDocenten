package com.example.workshopdocenten.controller;

import com.example.workshopdocenten.model.Student;
import com.example.workshopdocenten.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentRepository repos;

    @GetMapping
    public ResponseEntity<Iterable<Student>> getStudents() {
        return ResponseEntity.ok(repos.findAll());
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student s) {
        repos.save(s);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + s.getStudentNr()).toUriString());
        return ResponseEntity.created(uri).body(s);
    }
}
