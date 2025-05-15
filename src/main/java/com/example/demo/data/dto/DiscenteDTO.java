package com.example.demo.data.dto;

import java.util.List;

public class DiscenteDTO {

    private Long id;
    private String nome;
    private String cognome;
    private Integer eta;
    private String cittaResidenza;
    private List<Long> corsiIds;

    /* Costruttori */
    public DiscenteDTO() {}

    public DiscenteDTO(Long id, String nome, String cognome, Integer eta, String cittaResidenza) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
        this.cittaResidenza = cittaResidenza;
    }

    /* Getters e Setters */
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

    public List<Long> getCorsiIds() {
        return corsiIds;
    }

    public void setCorsiIds(List<Long> corsiIds) {
        this.corsiIds = corsiIds;
    }

    @Override
    public String toString() {
        return "DiscenteDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", eta=" + eta +
                ", cittaResidenza='" + cittaResidenza + '\'' +
                ", corsiIds=" + corsiIds +
                '}';
    }
}
