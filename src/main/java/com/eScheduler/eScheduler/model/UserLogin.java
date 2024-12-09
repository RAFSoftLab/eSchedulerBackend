package com.eScheduler.eScheduler.model;

import com.eScheduler.eScheduler.model.enums.Roles;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "korisnik")
@Data
public class UserLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idkorisnik")
    private Long id;

    private String email;

    @Column(name = "uloga")
    @Enumerated(EnumType.STRING)
    private Roles role;

    @Column(name = "lozinka")
    private String password;

    @OneToOne(mappedBy = "userLogin", cascade = CascadeType.ALL)
    private Teacher teacher;

}
