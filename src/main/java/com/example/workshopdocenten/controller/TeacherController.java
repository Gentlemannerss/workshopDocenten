package com.example.workshopdocenten.controller;

import com.example.workshopdocenten.dto.TeacherDto;
import com.example.workshopdocenten.model.Teacher;
import com.example.workshopdocenten.repository.TeacherRepository;
import com.example.workshopdocenten.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

@RestController
@RequestMapping("/teachers") // Geef een root pad aan voor alle endpoints
public class TeacherController {

    private final TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }

    /*@Autowired
    private TeacherRepository repos;*/

    /*@GetMapping
    public ResponseEntity<Iterable<Teacher>> getTeachers() {
        return ResponseEntity.ok(repos.findAll());
    }*/
    @PostMapping
    public ResponseEntity<TeacherDto> createTeacher(@RequestBody TeacherDto teacherDto) {
        Long newId = service.createTeacher(teacherDto); //we moeten het doorsturen naar de service laag.
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + newId).toUriString());
        teacherDto.id = newId;
        return ResponseEntity.created(uri).body(teacherDto);
    }

    @PutMapping("/{id}/course/{course_id}")
    public ResponseEntity<TeacherDto> assignCourseToTeacher(@PathVariable Long id, @PathVariable Long course_id) {
        return ResponseEntity.ok(service.assignCourseToTeacher(id,course_id));
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getTeacher(@PathVariable Long id) {
        return ResponseEntity .ok(service.getTeacher(id));
    }*/

    /*@GetMapping("/before")
    public ResponseEntity<Iterable<Teacher>> getTeachersBefore(@RequestParam LocalDate date){
        return ResponseEntity.ok(repos.findByDobBefore(date));
    }*/
}
