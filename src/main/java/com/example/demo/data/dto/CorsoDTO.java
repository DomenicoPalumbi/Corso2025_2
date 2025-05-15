package com.example.demo.data.dto;

import com.example.demo.data.entity.Docente;

import java.util.List;

public class CorsoDTO {

    private Long id;
    private String nome;
    private Integer annoAccademico;
    private Long docenteId;
    private List<Long> discentiIds;

    // Per la vista
    private String docenteNomeCompleto;
    private List<String> nomiDiscenti;

    public CorsoDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Integer getAnnoAccademico() { return annoAccademico; }
    public void setAnnoAccademico(Integer annoAccademico) { this.annoAccademico = annoAccademico; }

    public Long getDocenteId() { return docenteId; }
    public void setDocenteId(Long docenteId) { this.docenteId = docenteId; }

    public List<Long> getDiscentiIds() { return discentiIds; }
    public void setDiscentiIds(List<Long> discentiIds) { this.discentiIds = discentiIds; }

    public String getDocenteNomeCompleto() { return docenteNomeCompleto; }
    public void setDocenteNomeCompleto(String docenteNomeCompleto) { this.docenteNomeCompleto = docenteNomeCompleto; }

    public List<String> getNomiDiscenti() { return nomiDiscenti; }
    public void setNomiDiscenti(List<String> nomiDiscenti) { this.nomiDiscenti = nomiDiscenti; }
}
