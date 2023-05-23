package com.example.workshopdocenten.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "teachers") //is optioneel, dit zorgt voor een naam in de tabel
public class Teacher {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dob;

    @ManyToMany
    @JoinTable(
            name = "teacher_course",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    @JsonIgnore // dit zorgt ervoor dat hij dit niet vertaalt naar json en opnieuw iets oneindig uitgevoerd wordt.
    private List<Course> courses;
}
