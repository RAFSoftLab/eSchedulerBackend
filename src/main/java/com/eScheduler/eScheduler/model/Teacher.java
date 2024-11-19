package com.eScheduler.eScheduler.model;


import jakarta.persistence.*;

@Entity
@Table(name = "nastavnik")
public class Teacher {
    @Id
    @SequenceGenerator(name = "teacher_sequence",
                       sequenceName = "teacher_sequence",
                        allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "teacher_sequence")
    @Column(name = "idnastavnik")
    private Long id;
    @Column(name = "ime")
    private String firstName;
    @Column(name = "prezime")
    private String lastName;
    @Column(name = "zvanje")
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
