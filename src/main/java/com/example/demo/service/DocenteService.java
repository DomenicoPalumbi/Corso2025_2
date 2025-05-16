package com.example.demo.service;

import com.example.demo.data.dto.DocenteDTO;
import com.example.demo.data.dto.DocenteFullDTO;
import com.example.demo.data.entity.Docente;
import com.example.demo.repository.DocenteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DocenteService {

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Metodo per convertire Docente in DocenteDTO
    public DocenteDTO convertToDTO(Docente docente) {
        return modelMapper.map(docente, DocenteDTO.class);
    }

    // Metodo per ottenere un Docente (versione base)
    public DocenteDTO getDocenteById(Long id) {
        Optional<Docente> docenteOptional = docenteRepository.findById(id);
        return docenteOptional.map(this::convertToDTO).orElse(null);
    }

    // Metodo per ottenere un DocenteFullDTO (per edit con email)
    public DocenteFullDTO getFullDocenteById(Long id) {
        Optional<Docente> docenteOptional = docenteRepository.findById(id);
        return docenteOptional.map(DocenteFullDTO::new).orElse(null);
    }

    // Metodo per ottenere tutti i docenti
    public List<DocenteDTO> getAllDocenti() {
        List<Docente> docenti = docenteRepository.findAll();
        List<DocenteDTO> docenteDTOs = new ArrayList<>();
        for (Docente docente : docenti) {
            docenteDTOs.add(convertToDTO(docente));
        }
        return docenteDTOs;
    }

    // Metodo per salvare un nuovo docente (usa FullDTO per includere email)
    public void saveDocente(DocenteFullDTO docenteDTO) {
        Docente docente = new Docente();
        docente.setNome(docenteDTO.getNome());
        docente.setCognome(docenteDTO.getCognome());
        docente.setEmail(docenteDTO.getEmail());
        docenteRepository.save(docente);
    }

    // Metodo per aggiornare un docente esistente (usa FullDTO per includere email)
    public void updateDocente(Long id, DocenteFullDTO docenteDTO) {
        Optional<Docente> existingDocente = docenteRepository.findById(id);
        if (existingDocente.isPresent()) {
            Docente docente = existingDocente.get();
            docente.setNome(docenteDTO.getNome());
            docente.setCognome(docenteDTO.getCognome());
            docente.setEmail(docenteDTO.getEmail());
            docenteRepository.save(docente);
        } else {
            // eventualmente logga il caso in cui non viene trovato
        }
    }

    // Metodo per eliminare un docente
    public void deleteDocente(Long id) {
        if (docenteRepository.existsById(id)) {
            docenteRepository.deleteById(id);
        } else {
            // eventualmente logga il caso in cui non viene trovato
        }
    }
}
