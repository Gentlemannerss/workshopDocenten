package com.example.workshopdocenten.controller;

import com.example.workshopdocenten.dto.LesDto;
import com.example.workshopdocenten.model.Course;
import com.example.workshopdocenten.model.Les;
import com.example.workshopdocenten.repository.CourseRepository;
import com.example.workshopdocenten.repository.LesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lessons")
public class LesController {
    private final LesRepository lesRepository;
    private CourseRepository courseRepository;


    public LesController(LesRepository lesRepository, CourseRepository courseRepository) {
        this.lesRepository = lesRepository;
        this.courseRepository = courseRepository;
    }

    @PostMapping
    public ResponseEntity<Long> createLes(@RequestBody LesDto lesDto) {
        Les les = new Les();
        les.setTopic(lesDto.topic);


        Course course = courseRepository.findById(lesDto.CourseId).get();
        les.setCourse(course);
        lesRepository.save(les);

        return new ResponseEntity<>(les.getId(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<Les>> getAllLessons() {
        return new ResponseEntity<>(lesRepository.findAll(), HttpStatus.FOUND);
    }
}
