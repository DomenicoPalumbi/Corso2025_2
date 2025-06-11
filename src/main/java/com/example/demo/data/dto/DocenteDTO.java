package com.example.demo.data.dto;

import com.example.demo.data.entity.Docente;


public class DocenteDTO {
    private Long id;
    private String nomeDocente;
    private String cognomeDocente;
    private String emailDocente;
    public DocenteDTO() {
    }
    public DocenteDTO(Docente docente) {
        this.id = docente.getId();
        this.nomeDocente = docente.getNomeDocente();
        this.cognomeDocente = docente.getCognomeDocente();
        this.emailDocente = docente.getEmailDocente();
    }

    public DocenteDTO(Long id,String nomeDocente, String cognomeDocente, String emailDocente) {
        this.id = id;
        this.nomeDocente = nomeDocente;
        this.cognomeDocente = cognomeDocente;
        this.emailDocente = emailDocente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailDocente() {
        return emailDocente;
    }

    public void setEmailDocente(String emailDocente) {
        this.emailDocente = emailDocente;
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

    public void setCognome(String cognomeDocente) {
        this.cognomeDocente = cognomeDocente;
    }

}

