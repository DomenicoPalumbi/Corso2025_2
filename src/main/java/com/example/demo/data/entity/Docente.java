package com.example.demo.data.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "docenti")
public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "nome")
    private String nomeDocente;

    @Column(name = "cognome", nullable = false)
    private String cognomeDocente;

    @Column(name = "email", unique = true)
    private String emailDocente;

    public Docente() {
    }

    public Docente(Long id, String nomeDocente, String cognomeDocente, String emailDocente) {
        this.nomeDocente = nomeDocente;
        this.cognomeDocente = cognomeDocente;
        this.emailDocente = emailDocente;
    }

    public Docente(String nomeDocente, String cognomeDocente) {
        this.nomeDocente = nomeDocente;
        this.cognomeDocente = cognomeDocente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeDocente() {
        return nomeDocente;
    }

    public void setNomeDocente(String nomeDocente) {
        this.nomeDocente = nomeDocente;
    }

    public String getCognomeDocente() {
        return cognomeDocente;
    }

    public void setCognomeDocente(String cognomeDocente) {
        this.cognomeDocente = cognomeDocente;
    }

    public String getEmailDocente() {
        return emailDocente;
    }

    public void setEmailDocente(String emailDocente) {
        this.emailDocente = emailDocente;
    }

}