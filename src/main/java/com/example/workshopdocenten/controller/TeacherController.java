package com.example.workshopdocenten.controller;

import com.example.workshopdocenten.model.Teacher;
import com.example.workshopdocenten.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

@RestController
@RequestMapping("/teachers") // Geef een root pad aan voor alle endpoints
public class TeacherController {
    @Autowired
    private TeacherRepository repos;
    @GetMapping
    public ResponseEntity<Iterable<Teacher>> getTeachers() {
        return ResponseEntity.ok(repos.findAll());
    }
    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher t) {
        repos.save(t);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + t.getId()).toUriString());
        return ResponseEntity.created(uri).body(t);
    }

    @GetMapping("/before")
    public ResponseEntity<Iterable<Teacher>> getTeachersBefore(@RequestParam LocalDate date){
        return ResponseEntity.ok(repos.findByDobBefore(date));
    }
}
