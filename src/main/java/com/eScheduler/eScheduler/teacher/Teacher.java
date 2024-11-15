package com.eScheduler.eScheduler.teacher;


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
    private Long idnastavnik;
    private String ime;
    private String prezime;
    private String zvanje;

    public Long getIdnastavnik() {
        return idnastavnik;
    }

    public void setIdnastavnik(Long idnastavnik) {
        this.idnastavnik = idnastavnik;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getZvanje() {
        return zvanje;
    }

    public void setZvanje(String zvanje) {
        this.zvanje = zvanje;
    }
}
