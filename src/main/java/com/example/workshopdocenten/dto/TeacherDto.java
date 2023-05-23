package com.example.workshopdocenten.dto;

import com.example.workshopdocenten.model.Course;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class TeacherDto {
    public Long id;
    public String firstName;
    public String lastName;
    public LocalDate dob;
    public List<Course> courseTitles;

}
