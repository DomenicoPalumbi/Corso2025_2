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

    @Column(nullable = false, name = "nomeDiscente")
    private String nomeDiscente;

    @Column(name = "cognomeDiscente", nullable = false)
    private String cognomeDiscente;

    @Column(name = "matricola", unique = true)
    private Integer matricola;

    @Column(name = "eta", nullable = true)
    private Integer eta;

    @Column(name = "citta_residenza", nullable = true)
    private String cittaResidenza;

   /* @ManyToMany(mappedBy = "discenti")
    private List<Corso> corsi = new ArrayList<>();
*/
    /* costruttori */
    public Discente() {}

    public Discente(String nomeDiscente, String cognomeDiscente, Integer matricola, Integer eta, String cittaResidenza) {
        this.nomeDiscente = nomeDiscente;
        this.cognomeDiscente = cognomeDiscente;
        this.matricola = matricola;
        this.eta = eta;
        this.cittaResidenza = cittaResidenza;
    }

    public Discente(String nomeDiscente, String cognomeDiscente, Integer eta, String cittaResidenza) {
        this.nomeDiscente = nomeDiscente;
        this.cognomeDiscente = cognomeDiscente;
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

    public String getNomeDiscente() {
        return nomeDiscente;
    }

    public void setNomeDiscente(String nomeDiscente) {
        this.nomeDiscente = nomeDiscente;
    }

    public String getCognomeDiscente() {
        return cognomeDiscente;
    }

    public void setCognomeDiscente(String cognomeDiscente) {
        this.cognomeDiscente = cognomeDiscente;
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

    /*public List<Corso> getCorsi() {
        return corsi;
    }

    public void setCorsi(List<Corso> corsi) {
        this.corsi = corsi;
    }

     */
}
