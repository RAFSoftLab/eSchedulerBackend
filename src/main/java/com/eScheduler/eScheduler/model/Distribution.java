package com.eScheduler.eScheduler.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "raspodela")
@Data
public class Distribution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

}
