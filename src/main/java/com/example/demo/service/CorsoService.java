package com.example.demo.service;

import com.example.demo.data.entity.Corso;
import com.example.demo.repository.CorsoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CorsoService {

    @Autowired
    private CorsoRepository corsoRepository;

    public List<Corso> findAll() {
        return corsoRepository.findAll();
    }

    public Optional<Corso> findById(Long id) {
        return corsoRepository.findById(id);
    }

    public void save(Corso corso) {
        // Aggiungi logica di validazione se necessaria
        if (corso.getId() != null) {
            throw new IllegalArgumentException("Un nuovo corso non deve avere un ID.");
        }
        corsoRepository.save(corso);  // Salva il nuovo corso
    }
    public void update(Corso corso) {
        // Se il corso ha un ID, significa che deve essere aggiornato
        if (corso.getId() == null) {
            throw new IllegalArgumentException("Un corso esistente deve avere un ID.");
        }

        // Salva il corso, se esiste il corso con l'ID, verrà aggiornato
        corsoRepository.save(corso);  // Salva il corso esistente (Spring Data JPA gestisce l'aggiornamento)
    }

    public void deleteById(Long id) {
        corsoRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return corsoRepository.existsById(id);
    }

}
