package com.eScheduler.eScheduler.model;

import com.eScheduler.eScheduler.model.enums.Roles;
import jakarta.persistence.*;

@Entity
@Table(name = "korisnik")
public class UserLogin {
    @Id
    @SequenceGenerator(name = "userLogin_sequence",
            sequenceName = "userLogin_sequence",
            allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "userLogin_sequence")
    @Column(name = "idkorisnik")
    private Long id;

    private String email;

    @Column(name = "uloga")
    @Enumerated(EnumType.STRING)

    private Roles role;

    @Column(name = "lozinka")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
