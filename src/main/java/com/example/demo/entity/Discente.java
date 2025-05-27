package com.example.demo.data.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "discenti")
public class Discente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "nome")
    private String nome;

    @Column(name = "cognome", nullable = false)
    private String cognome;

    @Column(name = "matricola", unique = true)
    private Integer matricola;

    @Column(name = "eta", nullable = true)
    private Integer eta;

    @Column(name = "citta_residenza", nullable = true)
    private String cittaResidenza;

    @ManyToMany(mappedBy = "discenti")
    private List<Corso> corsi = new ArrayList<>();

    /* costruttori */
    public Discente() {}

    public Discente(String nome, String cognome, Integer matricola, Integer eta, String cittaResidenza) {
        this.nome = nome;
        this.cognome = cognome;
        this.matricola = matricola;
        this.eta = eta;
        this.cittaResidenza = cittaResidenza;
    }

    public Discente(String nome, String cognome, Integer eta, String cittaResidenza) {
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
        this.cittaResidenza = cittaResidenza;
    }

    /* getter e setter */
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

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Integer getMatricola() {
        return matricola;
    }

    public void setMatricola(Integer matricola) {
        this.matricola = matricola;
    }

    public Integer getEta() {
        return eta;
    }

    public void setEta(Integer eta) {
        this.eta = eta;
    }

    public String getCittaResidenza() {
        return cittaResidenza;
    }

    public void setCittaResidenza(String cittaResidenza) {
        this.cittaResidenza = cittaResidenza;
    }

    public List<Corso> getCorsi() {
        return corsi;
    }

    public void setCorsi(List<Corso> corsi) {
        this.corsi = corsi;
    }
}
