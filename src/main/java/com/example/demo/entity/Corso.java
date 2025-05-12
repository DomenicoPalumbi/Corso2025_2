package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "corsi")
public class Corso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "nome")
    private String nome;

    @Column(name = "anno_accademico", nullable = false)
    private Integer annoAccademico;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_doc", referencedColumnName = "id", nullable = false)
    private Docente docente;

    /* costruttori */
    public Corso() {}

    // Modificato per passare un oggetto Docente invece di un Integer idDocente
    public Corso(String nome, Integer annoAccademico, Docente docente) {
        this.nome = nome;
        this.annoAccademico = annoAccademico;
        this.docente = docente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnnoAccademico() {
        return annoAccademico;
    }

    public void setAnnoAccademico(Integer annoAccademico) {
        this.annoAccademico = annoAccademico;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }
}
