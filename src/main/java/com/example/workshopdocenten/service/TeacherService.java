package com.example.workshopdocenten.service;

import com.example.workshopdocenten.dto.TeacherDto;
import com.example.workshopdocenten.exception.ResourceNotFoundException;
import com.example.workshopdocenten.model.Course;
import com.example.workshopdocenten.model.Teacher;
import com.example.workshopdocenten.repository.CourseRepository;
import com.example.workshopdocenten.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    public TeacherService(TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    public TeacherDto assignCourseToTeacher(Long id, Long course_id) {
        Optional<Teacher>teacher = teacherRepository.findById(id);
        Optional<Course>course = courseRepository.findById(course_id);
        if (teacher.isEmpty() || course.isEmpty()) {
            throw new ResourceNotFoundException ("Jammer maar helaas");
        }
        Teacher teacher1 = teacher.get();
        Course course1 = course.get();

        ArrayList<Course> courseList= new ArrayList<>();
        courseList.add(course1);
        teacher1.setCourses(courseList);

        teacherRepository.save(teacher1);

        return transferTeacherToTeacherDto(teacher1);
    }

    /*public TeacherDto getTeacher(Long id) {
        Teacher t = teacherRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));

        //Moet nu de teacher in teacherDTO stoppen.
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.id = t.getId();
        teacherDto.firstName = t.getFirstName();
        teacherDto.lastName = t.getLastName();
        teacherDto.dob = t.getDob();

        for(Course c : t.getCourses()) {
            teacherDto.courseTitles.add(c.getTitle());
        }

        return teacherDto;
    }*/


    public Long createTeacher(TeacherDto teacherDto) {
        Teacher teacher = new Teacher(); //je moet een teacher object maken om hier bij te kunnen.
        teacher.setFirstName(teacherDto.firstName);
        teacher.setLastName(teacherDto.lastName);
        teacher.setDob(teacherDto.dob);

        teacherRepository.save(teacher);

        return teacher.getId();
    }

    // Dit hoort in alle SErvices terug , eentje naar DTO en eentje naar entiteit
    public TeacherDto transferTeacherToTeacherDto(Teacher teacher) {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setId(teacher.getId());
        teacherDto.setFirstName(teacher.getFirstName());
        teacherDto.setLastName(teacher.getLastName());
        teacherDto.setDob(teacher.getDob());
        // You may need to convert the List<Course> to List<CourseDto> if needed
        /*teacherDto.setCourses(teacher.getCourses());*/
        teacherDto.setCourseTitles(teacher.getCourses());
        return teacherDto;
    }
}
