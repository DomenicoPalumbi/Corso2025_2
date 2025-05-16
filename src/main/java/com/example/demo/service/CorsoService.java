package com.example.demo.service;

import com.example.demo.data.dto.CorsoDTO;
import com.example.demo.data.dto.CorsoFullDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Discente;
import com.example.demo.data.entity.Docente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DiscenteRepository;
import com.example.demo.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    // Metodo per visualizzazione lista
    public List<CorsoDTO> getAllCorsiDTO() {
        return corsoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
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
            dto.setDiscentiIds(corso.getDiscenti().stream()
                    .map(Discente::getId)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    // FullDTO per form modifica
    public CorsoFullDTO getCorsoById(Long id) {
        return corsoRepository.findById(id)
                .map(CorsoFullDTO::new)
                .orElse(null);
    }

    public void saveCorso(CorsoFullDTO dto) {
        Corso corso = new Corso();
        corso.setNome(dto.getNome());
        corso.setAnnoAccademico(dto.getAnnoAccademico());

        if (dto.getDocenteId() != null) {
            docenteRepository.findById(dto.getDocenteId()).ifPresent(corso::setDocente);
        }

        if (dto.getDiscentiIds() != null && !dto.getDiscentiIds().isEmpty()) {
            List<Discente> discenti = discenteRepository.findAllById(dto.getDiscentiIds());
            corso.setDiscenti(discenti);
        }

        corsoRepository.save(corso);
    }

    public void updateCorso(Long id, CorsoFullDTO dto) {
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

            corsoRepository.save(corso);
        }
    }

    public void deleteCorso(Long id) {
        if (corsoRepository.existsById(id)) {
            corsoRepository.deleteById(id);
        }
    }
}
