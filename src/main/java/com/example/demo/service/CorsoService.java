package com.example.demo.service;

import com.example.demo.data.dto.CorsoDTO;
import com.example.demo.data.dto.CorsoFullDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Discente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DiscenteRepository;
import com.example.demo.repository.DocenteRepository;
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

        if (corsoFullDTO.getDocenteId() != null) {
            docenteRepository.findById(corsoFullDTO.getDocenteId()).ifPresent(corso::setDocente);
        }

        if (corsoFullDTO.getDiscentiIds() != null && !corsoFullDTO.getDiscentiIds().isEmpty()) {
            List<Discente> discenti = discenteRepository.findAllById(corsoFullDTO.getDiscentiIds());
            corso.setDiscenti(discenti);
        }

        Corso savedCorso = corsoRepository.save(corso);
        return convertToDTO(savedCorso);
    }

    public CorsoDTO updateCorso(Long id, CorsoFullDTO dto) {
        Optional<Corso> optional = corsoRepository.findById(id);
        if (optional.isPresent()) {
            Corso corso = optional.get();
            corso.setNome(dto.getNome());
            corso.setAnnoAccademico(dto.getAnnoAccademico());

            if (dto.getDocenteId() != null) {
                docenteRepository.findById(dto.getDocenteId()).ifPresent(corso::setDocente);
            }

            if (dto.getDiscentiIds() != null) {
                List<Discente> discenti = discenteRepository.findAllById(dto.getDiscentiIds());
                corso.setDiscenti(discenti);
            } else {
                corso.setDiscenti(List.of());
            }

            Corso updatedCorso = corsoRepository.save(corso);
            return convertToDTO(updatedCorso);
        }
        return null;
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

        Optional<Corso> corso = corsoRepository.findById(id);
        if (corso.isPresent()) {
            corsoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Corso con ID " + id + " non trovato");
        }
    }
}