package com.example.demo.data.dto;

import com.example.demo.data.entity.Discente;

public class DiscenteFullDTO {
    private Long id;
    private String nomeDiscente;
    private String cognomeDiscente;
    private Integer matricola;
    private Integer eta;
    private String cittaResidenza;

    public DiscenteFullDTO() {
    }

    public DiscenteFullDTO(Discente discente) {
        this.id = discente.getId();
        this.nomeDiscente = discente.getNomeDiscente();
        this.cognomeDiscente = discente.getCognomeDiscente();
        this.matricola = discente.getMatricola();
        this.eta = discente.getEta();
        this.cittaResidenza = discente.getCittaResidenza();
    }

    // Getter e Setter

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

}
