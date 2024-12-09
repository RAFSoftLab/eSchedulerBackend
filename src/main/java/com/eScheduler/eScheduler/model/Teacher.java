package com.eScheduler.eScheduler.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "nastavnik")
@Data
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idnastavnik")
    private Long id;

    @Column(name = "ime")
    private String firstName;

    @Column(name = "prezime")
    private String lastName;

    @Column(name = "zvanje")
    private String title;

    @OneToOne
    @JoinColumn(name = "email", referencedColumnName = "email")
    private UserLogin userLogin;

}
