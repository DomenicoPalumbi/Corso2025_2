package com.example.demo.data.dto;

import com.example.demo.data.entity.Docente;
import java.util.List;
import java.util.stream.Collectors;

public class DocenteFullDTO {
    private Long id;
    private String nomeDocente;
    private String cognomeDocente;
    private String emailDocente;

    public DocenteFullDTO() {
    }
    public DocenteFullDTO(Docente docente) {
        this.id = docente.getId();
        this.nomeDocente = docente.getNomeDocente();
        this.cognomeDocente = docente.getCognomeDocente();
        this.emailDocente = docente.getEmailDocente();

    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomeDocente() { return nomeDocente; }
    public void setNomeDocente(String nomeDocente) { this.nomeDocente = nomeDocente; }

    public String getCognomeDocente() { return cognomeDocente; }
    public void setCognomeDocente(String cognomeDocente) { this.cognomeDocente = cognomeDocente; }

    public String getEmailDocente() { return emailDocente; }
    public void setEmailDocente(String emailDocente) { this.emailDocente = emailDocente; }
}
