package com.eScheduler.eScheduler.model;


import jakarta.persistence.*;

@Entity
@Table(name = "raspodela")
public class Distribution {
    @Id
    @SequenceGenerator(name = "distribution_sequence",
            sequenceName = "distribution_sequence",
            allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "distribution_sequence")
    @Column(name = "id_raspodela")
    private Long id;
    @Column(name = "id_nastavnik")
    private Long teacherId;
    @Column(name = "id_predmet")
    private Long subjectId;
    @Column(name = "vrsta_casa")
    private String classType;
    @Column(name = "broj_termina")
    private Integer sessionCount;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public Integer getSessionCount() {
        return sessionCount;
    }

    public void setSessionCount(Integer sessionCount) {
        this.sessionCount = sessionCount;
    }
}
