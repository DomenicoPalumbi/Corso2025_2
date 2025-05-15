package com.example.demo.converter;
import com.example.demo.data.dto.DiscenteDTO;
import com.example.demo.data.dto.DocenteDTO;
import com.example.demo.data.entity.Discente;
import com.example.demo.data.entity.Docente;

public class Converter {

    public static DocenteDTO toDTO(Docente docente) {
        if (docente == null) return null;
        return new DocenteDTO(
                docente.getId(),
                docente.getNome(),
                docente.getCognome()
        );
    }
    public static Docente toEntity(DocenteDTO dto) {
        if (dto == null) return null;
        return new Docente(
                dto.getNome(),
                dto.getCognome()
        );
    }
    public static DiscenteDTO toDTO(Discente discente) {
        if (discente == null) return null;
        return new DiscenteDTO(
                discente.getId(),
                discente.getNome(),
                discente.getCognome(),
                discente.getEta(),
                discente.getCittaResidenza()
        );
    }

    public static Discente toEntity(DiscenteDTO dto) {
        if (dto == null) return null;
        Discente discente = new Discente(
                dto.getNome(),
                dto.getCognome(),
                dto.getEta(),
                dto.getCittaResidenza()
        );
        discente.setId(dto.getId());
        return discente;
    }
}

