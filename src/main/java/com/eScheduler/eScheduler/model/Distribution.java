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

    @ManyToOne
    @JoinColumn(name = "id_nastavnik", referencedColumnName = "idnastavnik")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "id_predmet", referencedColumnName = "idpredmet")
    private Subject subject;

    @Column(name = "vrsta_casa")
    private String classType;

    @Column(name = "broj_termina")
    private Integer sessionCount;

}
