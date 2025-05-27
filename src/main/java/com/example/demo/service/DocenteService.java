package com.example.demo.service;

import com.example.demo.data.dto.DocenteDTO;
import com.example.demo.data.dto.DocenteFullDTO;
import com.example.demo.data.entity.Docente;
import com.example.demo.repository.DocenteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
        List<Docente> docenti = docenteRepository.findAll(Sort.by("id")); // Ordina per ID
        List<DocenteDTO> docenteDTOs = new ArrayList<>();
        for (Docente docente : docenti) {
            docenteDTOs.add(convertToDTO(docente));
        }
        return docenteDTOs;
    }


    // Metodo per salvare un nuovo docente (usa FullDTO per includere email)
    public DocenteFullDTO saveDocente(DocenteFullDTO docenteFullDTO) {
        Docente docente = new Docente();
        docente.setNome(docenteFullDTO.getNome());
        docente.setCognome(docenteFullDTO.getCognome());
        docente.setEmail(docenteFullDTO.getEmail());
        docenteRepository.save(docente);
        return docenteFullDTO;
    }

    // Metodo per aggiornare un docente esistente (usa FullDTO per includere email)
    public DocenteDTO updateDocente(Long id, DocenteFullDTO docenteFullDTO) {
        Docente existingDocente = docenteRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        existingDocente.setNome(docenteFullDTO.getNome());
        existingDocente.setCognome(docenteFullDTO.getCognome());
        existingDocente.setEmail(docenteFullDTO.getEmail());
        Docente updateDocente = docenteRepository.save(existingDocente);
        return convertToDTO(updateDocente);
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
