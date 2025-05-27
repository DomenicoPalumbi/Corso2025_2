package com.example.demo.data.dto;

import com.example.demo.data.entity.Discente;

public class DiscenteDTO {
    private Long id;
    private String nome;
    private String cognome;

    public DiscenteDTO() {}

    public DiscenteDTO(Discente discente) {
        this.id = discente.getId();
        this.nome = discente.getNome();
        this.cognome = discente.getCognome();
    }

    // getter e setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }
}