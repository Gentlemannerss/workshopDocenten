package com.example.workshopdocenten.controller;

import com.example.workshopdocenten.dto.CourseDto;
import com.example.workshopdocenten.model.Course;
import com.example.workshopdocenten.model.Teacher;
import com.example.workshopdocenten.repository.CourseRepository;
import com.example.workshopdocenten.repository.TeacherRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseRepository courseRepos;
    private final TeacherRepository teacherRepos;

    public CourseController(CourseRepository courseRepos, TeacherRepository teacherRepos) {
        this.courseRepos = courseRepos;
        this.teacherRepos = teacherRepos;
    }

    @PostMapping
    public ResponseEntity<Long> createCourse(@RequestBody CourseDto courseDto) {
        Course course = new Course();
        course.setTitle(courseDto.title);
        course.setStudyPoints(courseDto.studyPoints);

        /*Teacher teachers = teacherRepos.findById(courseDto.TeacherId).get(); //happy flow
        course.setTeacher(teachers);*/

        Teacher teacher = teacherRepos.findById(courseDto.TeacherId).orElse(null); // retrieve the teacher object
        if (teacher != null) {
            course.getTeachers().add(teacher); // add the teacher to the course's teachers list
        }

        courseRepos.save(course);

        return new ResponseEntity<>(course.getId(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<Course>> getAllCourses() {
        return new ResponseEntity<>(courseRepos.findAll(), HttpStatus.FOUND);
    }
}
