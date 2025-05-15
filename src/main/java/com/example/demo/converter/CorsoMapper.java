package com.example.demo.converter;
import com.example.demo.data.dto.CorsoDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Discente;
import com.example.demo.data.entity.Docente;
import org.mapstruct.*;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CorsoMapper {

    // Entity → DTO
    @Mapping(source = "docente.id", target = "docenteId")
    @Mapping(source = "docente", target = "docenteNomeCompleto", qualifiedByName = "docenteToNomeCompleto")
    @Mapping(source = "discenti", target = "discentiIds", qualifiedByName = "discentiToIds")
    @Mapping(source = "discenti", target = "nomiDiscenti", qualifiedByName = "discentiToNomi")
    CorsoDTO toDTO(Corso corso);

    // DTO → Entity
    @Mapping(target = "docente", source = "docenteId", qualifiedByName = "idToDocente")
    @Mapping(target = "discenti", source = "discentiIds", qualifiedByName = "idsToDiscenti")
    Corso toEntity(CorsoDTO dto);

    // DOCENTE → nome completo
    @Named("docenteToNomeCompleto")
    public static String docenteToNomeCompleto(Docente docente) {
        if (docente == null) return "";
        return docente.getNome() + " " + docente.getCognome();
    }

    // LISTA DI DISCENTI → LISTA DI ID
    @Named("discentiToIds")
    public static List<Long> discentiToIds(List<Discente> discenti) {
        if (discenti == null) return List.of();
        return discenti.stream()
                .map(Discente::getId)
                .collect(Collectors.toList());
    }

    // LISTA DI DISCENTI → LISTA DI NOMI COMPLETI
    @Named("discentiToNomi")
    public static List<String> discentiToNomi(List<Discente> discenti) {
        if (discenti == null) return List.of();
        return discenti.stream()
                .map(d -> d.getNome() + " " + d.getCognome())
                .collect(Collectors.toList());
    }

    // ID DOCENTE → oggetto Docente (solo con ID settato)
    @Named("idToDocente")
    public static Docente idToDocente(Long id) {
        if (id == null) return null;
        Docente d = new Docente();
        d.setId(id);
        return d;
    }

    // LISTA DI ID → LISTA DI DISCENTI (solo ID settati)
    @Named("idsToDiscenti")
    public static List<Discente> idsToDiscenti(List<Long> ids) {
        if (ids == null) return List.of();
        return ids.stream().map(id -> {
            Discente d = new Discente();
            d.setId(id);
            return d;
        }).collect(Collectors.toList());
    }
}
