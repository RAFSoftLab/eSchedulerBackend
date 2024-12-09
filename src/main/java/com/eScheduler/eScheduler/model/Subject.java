package com.eScheduler.eScheduler.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "predmet")
@Data
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpredmet")
    private Long id;

    @Column(name = "naziv")
    private String name;

    @Column(name = "stud_program")
    private String studyProgram;

    @Column(name = "semestar")
    private Integer semester;

    @Column(name = "fond_predavanja")
    private Integer lectureHours;

    @Column(name = "fond_vezbe")
    private Integer exerciseHours;

    @Column(name = "fond_praktikum")
    private Integer practicumHours;

    @Column(name = "obavezni")
    private String mandatory;

    @Column(name = "broj_termina_predavanja")
    private Integer lectureSessions;

    @Column(name = "broj_termina_vezbe")
    private Integer exerciseSessions;

}
