package com.example.demo.data.dto;

import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Docente;
import com.example.demo.data.entity.Discente;

import java.util.ArrayList;
import java.util.List;

public class CorsoFullDTO {

    private Long id;
    private String nome;
    private Integer annoAccademico;

    // Entità complete (opzionali da usare internamente)
    private Docente docente;
    private List<Discente> discenti = new ArrayList<>();

    // Per il binding da JSP
    private Long docenteId;
    private List<Long> discentiIds = new ArrayList<>();

    public CorsoFullDTO() {
    }

    public CorsoFullDTO(Corso corso) {
        this.id = corso.getId();
        this.nome = corso.getNome();
        this.annoAccademico = corso.getAnnoAccademico();
        this.docente = corso.getDocente();
        this.discenti = corso.getDiscenti();
        this.docenteId = corso.getDocente() != null ? corso.getDocente().getId() : null;

        if (corso.getDiscenti() != null) {
            for (Discente d : corso.getDiscenti()) {
                this.discentiIds.add(d.getId());
            }
        }
    }

    // Getters e Setters

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

    public Integer getAnnoAccademico() {
        return annoAccademico;
    }

    public void setAnnoAccademico(Integer annoAccademico) {
        this.annoAccademico = annoAccademico;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public List<Discente> getDiscenti() {
        return discenti;
    }

    public void setDiscenti(List<Discente> discenti) {
        this.discenti = discenti;
    }

    public Long getDocenteId() {
        return docenteId;
    }

    public void setDocenteId(Long docenteId) {
        this.docenteId = docenteId;
    }

    public List<Long> getDiscentiIds() {
        return discentiIds;
    }

    public void setDiscentiIds(List<Long> discentiIds) {
        this.discentiIds = discentiIds;
    }
}
