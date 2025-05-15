package com.example.demo.service;

import com.example.demo.data.dto.DocenteDTO;
import com.example.demo.data.entity.Docente;
import com.example.demo.converter.Converter;
import com.example.demo.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocenteService {

    @Autowired
    private DocenteRepository docenteRepository;

    // Ottieni tutti i docenti e restituisci come lista di DTO
   // public List<DocenteDTO> getAllDocenti() {
     //   List<Docente> docenti = docenteRepository.findAll();
       // return docenti.stream()
         //       .map(Converter::toDTO)  // Converte ogni Docente in DocenteDTO
           //     .collect(Collectors.toList());
    //}
    public List<DocenteDTO> getAllDocenti() {
        List<Docente> docenti = docenteRepository.findAll();
        List<DocenteDTO> docentiDTO = new ArrayList<>();
        for (Docente docente : docenti) {
            docentiDTO.add(new DocenteDTO(docente));  // Mappa l'entità a DTO
        }
        return docentiDTO;
    }

    // Ottieni un docente per ID
    public DocenteDTO getDocenteById(Long id) {
        Optional<Docente> docente = docenteRepository.findById(id);
        return docente.map(Converter::toDTO).orElse(null);  // Restituisce null se non trovato
    }

    // Salva un nuovo docente
    public void saveDocente(DocenteDTO docenteDTO) {
        Docente docente = Converter.toEntity(docenteDTO);  // Converte il DTO in entità Docente
        docenteRepository.save(docente);  // Salva nel database
    }

    // Aggiorna un docente esistente
    public void updateDocente(Long id, DocenteDTO docenteDTO) {
        Docente docente = docenteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Docente non trovato"));
        docente.setNome(docenteDTO.getNome());
        docente.setCognome(docenteDTO.getCognome());
        docenteRepository.save(docente);  // Salva il docente aggiornato
    }

    // Elimina un docente per ID
    public void deleteDocente(Long id) {
        docenteRepository.deleteById(id);  // Elimina il docente dal database
    }
}
