package com.example.demo.data.dto;

import com.example.demo.data.entity.Docente;
import java.util.List;
import java.util.stream.Collectors;

public class DocenteFullDTO {
    private Long id;
    private String nome;
    private String cognome;
    private String email;

    public DocenteFullDTO() {
    }
    public DocenteFullDTO(Docente docente) {
        this.id = docente.getId();
        this.nome = docente.getNome();
        this.cognome = docente.getCognome();
        this.email = docente.getEmail();

    }
    // getter e setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
