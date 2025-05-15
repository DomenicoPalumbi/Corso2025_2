package com.example.demo.service;

import com.example.demo.data.dto.DocenteDTO;
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

    // Metodo per ottenere un Docente dal database tramite ID
    public DocenteDTO getDocenteById(Long id) {
        Optional<Docente> docenteOptional = docenteRepository.findById(id);
        if (docenteOptional.isPresent()) {
            // Se il docente esiste, ritorna il DTO
            return convertToDTO(docenteOptional.get());
        }
        // Restituisce null se il docente non viene trovato
        return null;
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

    // Metodo per salvare un nuovo docente
    public void saveDocente(DocenteDTO docenteDTO) {
        Docente docente = modelMapper.map(docenteDTO, Docente.class);
        docenteRepository.save(docente);
    }

    // Metodo per aggiornare un docente esistente
    public void updateDocente(Long id, DocenteDTO docenteDTO) {
        Optional<Docente> existingDocente = docenteRepository.findById(id);
        if (existingDocente.isPresent()) {
            Docente docente = existingDocente.get();
            docente.setNome(docenteDTO.getNome());
            docente.setCognome(docenteDTO.getCognome());
            // Aggiungi altri campi da aggiornare se necessario
            docenteRepository.save(docente);
        } else {
            // Ritorna senza fare nulla o aggiungi un messaggio di log
            // Non lancia un'eccezione, ma potrebbe essere utile loggare il tentativo di aggiornamento non riuscito
        }
    }

    // Metodo per eliminare un docente
    public void deleteDocente(Long id) {
        if (docenteRepository.existsById(id)) {
            docenteRepository.deleteById(id);
        } else {
            // Ritorna senza fare nulla, se non troviamo il docente (potresti loggare l'errore)
        }
    }
}
