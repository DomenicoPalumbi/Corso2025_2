package com.example.demo.data.dto;

import com.example.demo.data.entity.Docente;

public class DocenteDTO {
    private Long id;
    private String nome;
    private String cognome;

    public DocenteDTO() {
    }
    public DocenteDTO(Docente docente) {
        this.id = docente.getId();
        this.nome = docente.getNome();
        this.cognome = docente.getCognome();
    }

    public DocenteDTO(Long id,String nome, String cognome) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

