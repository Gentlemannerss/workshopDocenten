package com.example.workshopdocenten.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private int studyPoints;
    @ManyToMany(mappedBy = "courses")
    private List<Teacher> teachers = new ArrayList<>();

    @OneToMany(mappedBy = "course") //Mapped by heb je nodig anders creer je een many to many relatie en een koppel tabel in de database
    private List<Les> lessons;
}
