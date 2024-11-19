package com.eScheduler.eScheduler.model;
import jakarta.persistence.*;

@Entity
@Table(name = "predmet")
public class Subject {
    @Id
    @SequenceGenerator(name = "subject_sequence",
            sequenceName = "subject_sequence",
            allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "subject_sequence")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudyProgram() {
        return studyProgram;
    }

    public void setStudyProgram(String studyProgram) {
        this.studyProgram = studyProgram;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Integer getLectureHours() {
        return lectureHours;
    }

    public void setLectureHours(Integer lectureHours) {
        this.lectureHours = lectureHours;
    }

    public Integer getExerciseHours() {
        return exerciseHours;
    }

    public void setExerciseHours(Integer exerciseHours) {
        this.exerciseHours = exerciseHours;
    }

    public Integer getPracticumHours() {
        return practicumHours;
    }

    public void setPracticumHours(Integer practicumHours) {
        this.practicumHours = practicumHours;
    }

    public String getMandatory() {
        return mandatory;
    }

    public void setMandatory(String mandatory) {
        this.mandatory = mandatory;
    }

    public Integer getLectureSessions() {
        return lectureSessions;
    }

    public void setLectureSessions(Integer lectureSessions) {
        this.lectureSessions = lectureSessions;
    }

    public Integer getExerciseSessions() {
        return exerciseSessions;
    }

    public void setExerciseSessions(Integer exerciseSessions) {
        this.exerciseSessions = exerciseSessions;
    }
}
