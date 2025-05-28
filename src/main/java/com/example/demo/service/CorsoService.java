package com.example.demo.service;

import com.example.demo.data.dto.CorsoDTO;
import com.example.demo.data.dto.CorsoFullDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Discente;
import com.example.demo.data.entity.Docente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DiscenteRepository;
import com.example.demo.repository.DocenteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CorsoService {
    @Autowired
    private CorsoRepository corsoRepository;
//commento prova
    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private DiscenteRepository discenteRepository;

    public List<CorsoDTO> getAllCorsiDTO() {
        return corsoRepository.findAll(Sort.by("id")).stream()
                .filter(Objects::nonNull)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CorsoDTO saveCorso(CorsoFullDTO corsoFullDTO) {
        Corso corso = new Corso();
        corso.setNome(corsoFullDTO.getNome());
        corso.setAnnoAccademico(corsoFullDTO.getAnnoAccademico());

        // Gestione docente
        if (corsoFullDTO.getNomeCompletoDocente() != null) {
            String[] nomeDocente = corsoFullDTO.getNomeCompletoDocente().split(" ", 2);
            Docente docente = getOrCreateDocente(nomeDocente[0], nomeDocente[1]);
            corso.setDocente(docente);
        }

        // Gestione discenti
        if (corsoFullDTO.getNomiCompletiDiscenti() != null) {
            List<Discente> discenti = corsoFullDTO.getNomiCompletiDiscenti().stream()
                    .map(nomeCompleto -> {
                        String[] nome = nomeCompleto.split(" ", 2);
                        return getOrCreateDiscente(nome[0], nome[1]);
                    })
                    .collect(Collectors.toList());
            corso.setDiscenti(discenti);
        }

        return convertToDTO(corsoRepository.save(corso));
    }

    public CorsoDTO updateCorso(Long id, CorsoFullDTO dto) {
        return corsoRepository.findById(id)
                .map(corso -> {
                    corso.setNome(dto.getNome());
                    corso.setAnnoAccademico(dto.getAnnoAccademico());

                    // Aggiorna docente
                    if (dto.getNomeCompletoDocente() != null) {
                        String[] nomeDocente = dto.getNomeCompletoDocente().split(" ", 2);
                        Docente docente = getOrCreateDocente(nomeDocente[0], nomeDocente[1]);
                        corso.setDocente(docente);
                    }

                    // Aggiorna discenti
                    if (dto.getNomiCompletiDiscenti() != null) {
                        List<Discente> discenti = dto.getNomiCompletiDiscenti().stream()
                                .map(nomeCompleto -> {
                                    String[] nome = nomeCompleto.split(" ", 2);
                                    return getOrCreateDiscente(nome[0], nome[1]);
                                })
                                .collect(Collectors.toList());
                        corso.setDiscenti(discenti);
                    } else {
                        corso.setDiscenti(List.of());
                    }

                    return convertToDTO(corsoRepository.save(corso));
                })
                .orElseThrow(() -> new EntityNotFoundException("Corso non trovato con id: " + id));
    }

    private Docente getOrCreateDocente(String nome, String cognome) {
        return docenteRepository.findByNomeAndCognome(nome, cognome)
                .orElseGet(() -> {
                    Docente nuovoDocente = new Docente();
                    nuovoDocente.setNome(nome);
                    nuovoDocente.setCognome(cognome);
                    return docenteRepository.save(nuovoDocente);
                });
    }

    private Discente getOrCreateDiscente(String nome, String cognome) {
        return discenteRepository.findByNomeAndCognome(nome, cognome)
                .orElseGet(() -> {
                    Discente nuovoDiscente = new Discente();
                    nuovoDiscente.setNome(nome);
                    nuovoDiscente.setCognome(cognome);
                    return discenteRepository.save(nuovoDiscente);
                });
    }

    private CorsoDTO convertToDTO(Corso corso) {
        CorsoDTO dto = new CorsoDTO();
        dto.setId(corso.getId());
        dto.setNome(corso.getNome());
        dto.setAnnoAccademico(corso.getAnnoAccademico());

        if (corso.getDocente() != null) {
            dto.setDocenteId(corso.getDocente().getId());
            dto.setDocenteNomeCompleto(corso.getDocente().getNome() + " " + corso.getDocente().getCognome());
        }

        if (corso.getDiscenti() != null) {
            dto.setDiscentiNomiCompleti(corso.getDiscenti().stream()
                    .map(d -> d.getNome() + " " + d.getCognome())
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    public void deleteCorso(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("L'ID del corso non può essere null");
        }

        if (!corsoRepository.existsById(id)) {
            throw new EntityNotFoundException("Corso con ID " + id + " non trovato");
        }

        corsoRepository.deleteById(id);
    }
}
