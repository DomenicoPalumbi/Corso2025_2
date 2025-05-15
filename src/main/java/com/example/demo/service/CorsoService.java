package com.example.demo.service;

import com.example.demo.data.dto.CorsoDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Discente;
import com.example.demo.data.entity.Docente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DiscenteRepository;
import com.example.demo.repository.DocenteRepository;
import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper modelMapper;

    private CorsoDTO convertToDTO(Corso corso) {
        CorsoDTO dto = modelMapper.map(corso, CorsoDTO.class);

        if (corso.getDiscenti() != null) {
            List<Long> ids = corso.getDiscenti().stream()
                    .map(Discente::getId)
                    .collect(Collectors.toList());
            dto.setDiscentiIds(ids);
        }

        if (corso.getDocente() != null) {
            dto.setDocenteId(corso.getDocente().getId());
            dto.setDocenteNomeCompleto(corso.getDocente().getNome() + " " + corso.getDocente().getCognome());
        }

        // Per nomi discenti (opzionale, puoi implementare se serve)
        if (corso.getDiscenti() != null) {
            List<String> nomi = corso.getDiscenti().stream()
                    .map(d -> d.getNome() + " " + d.getCognome())
                    .collect(Collectors.toList());
            dto.setNomiDiscenti(nomi);
        }

        return dto;
    }

    public CorsoDTO getCorsoById(Long id) {
        Optional<Corso> corsoOptional = corsoRepository.findById(id);
        return corsoOptional.map(this::convertToDTO).orElse(null);
    }

    public List<CorsoDTO> getAllCorsi() {
        return corsoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void saveCorso(CorsoDTO corsoDTO) {
        Corso corso = modelMapper.map(corsoDTO, Corso.class);

        Docente docente = docenteRepository.findById(corsoDTO.getDocenteId())
                .orElse(null);

        if (docente != null) {
            corso.setDocente(docente);
        }

        if (corsoDTO.getDiscentiIds() != null) {
            List<Discente> discenti = discenteRepository.findAllById(corsoDTO.getDiscentiIds());
            corso.setDiscenti(discenti);
        }

        corsoRepository.save(corso);
    }

    public void updateCorso(Long id, CorsoDTO corsoDTO) {
        Optional<Corso> existingCorso = corsoRepository.findById(id);

        if (existingCorso.isPresent()) {
            Corso corso = existingCorso.get();
            corso.setNome(corsoDTO.getNome());
            corso.setAnnoAccademico(corsoDTO.getAnnoAccademico());

            Docente docente = docenteRepository.findById(corsoDTO.getDocenteId())
                    .orElse(null);

            if (docente != null) {
                corso.setDocente(docente);
            }

            if (corsoDTO.getDiscentiIds() != null) {
                List<Discente> discenti = discenteRepository.findAllById(corsoDTO.getDiscentiIds());
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

    public boolean existsById(Long id) {
        return corsoRepository.existsById(id);
    }
}
