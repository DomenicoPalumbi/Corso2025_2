package com.example.demo.data.dto;

import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Discente;

import java.util.List;

public class DiscenteFullDTO {
    private Long id;
    private String nome;
    private String cognome;
    private Integer matricola;
    private Integer eta;
    private String cittaResidenza;
    private List<Corso> corsi;

    public DiscenteFullDTO() {
    }

    public DiscenteFullDTO(Discente discente) {
        this.id = discente.getId();
        this.nome = discente.getNome();
        this.cognome = discente.getCognome();
        this.matricola = discente.getMatricola();
        this.eta = discente.getEta();
        this.cittaResidenza = discente.getCittaResidenza();
        this.corsi = discente.getCorsi(); // ATTENZIONE: vedi nota sotto
    }

    // Getter e Setter

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
