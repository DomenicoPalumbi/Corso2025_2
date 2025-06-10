package com.example.demo.service;

import com.example.demo.data.dto.DiscenteDTO;
import com.example.demo.data.dto.DiscenteDTO;
import com.example.demo.data.entity.Discente;
import com.example.demo.repository.DiscenteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiscenteService {

    @Autowired
    private DiscenteRepository discenteRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Convertitore in DTO base
    private DiscenteDTO convertToDTO(Discente discente) {
        return modelMapper.map(discente, DiscenteDTO.class);
    }

    // Convertitore in FullDTO
    private DiscenteDTO convertToFullDTO(Discente discente) {
        return new DiscenteDTO(discente);
    }

    // Ottenere DTO base
    public DiscenteDTO getDiscenteById(Long id) {
        Optional<Discente> discente = discenteRepository.findById(id);
        return discente.map(this::convertToDTO).orElse(null);
    }

    // Ottenere FullDTO per la modifica
    public DiscenteDTO getFullDiscenteById(Long id) {
        Optional<Discente> discente = discenteRepository.findById(id);
        return discente.map(this::convertToFullDTO).orElse(null);
    }

    // Salvataggio nuovo Discente (usa FullDTO per includere matricola ecc.)
    public DiscenteDTO saveDiscente(DiscenteDTO DiscenteDTO) {
        // Verifica se esiste già un discente con lo stesso nome e cognome
        Discente existingDiscente = discenteRepository.findByNomeDiscenteAndCognomeDiscente(DiscenteDTO.getNomeDiscente(), DiscenteDTO.getCognomeDiscente());

        if (existingDiscente != null) {
            // Se il discente esiste già, ritorna il suo DTO senza creare un nuovo discente
            return convertToDTO(existingDiscente);
        }

        // Se non esiste, procedi con la creazione di un nuovo discente
        Discente discente = new Discente();
        discente.setNomeDiscente(DiscenteDTO.getNomeDiscente());
        discente.setCognomeDiscente(DiscenteDTO.getCognomeDiscente());

        Discente saved = discenteRepository.save(discente);
        return convertToDTO(saved);
    }

    public List<DiscenteDTO> getDiscentiByIds(List<Long> ids) {
        return discenteRepository.findAllById(ids)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    // Aggiornamento Discente esistente (da FullDTO)
    public DiscenteDTO updateDiscente(Long id, DiscenteDTO DiscenteDTO) {
        Discente existingDiscente = discenteRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        existingDiscente.setNomeDiscente(DiscenteDTO.getNomeDiscente());
        existingDiscente.setCognomeDiscente(DiscenteDTO.getCognomeDiscente());
        Discente updateDiscente = discenteRepository.save(existingDiscente);
        return convertToDTO(updateDiscente);
    }

    public void deleteDiscente(Long id) {
        if (!discenteRepository.existsById(id)) {
            throw new IllegalArgumentException("Discente non trovato con ID: " + id);
        }
        discenteRepository.deleteById(id);
    }


    public List<DiscenteDTO> getAllDiscenti() {
        return discenteRepository.findAll(Sort.by("id"))  // <-- Ordinamento per ID crescente
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<Discente> ordinaPerNomeAsc() {
        return discenteRepository.findAll()
                .stream()
                .sorted((d1, d2) -> d1.getNomeDiscente().compareToIgnoreCase(d2.getNomeDiscente()))
                .collect(Collectors.toList());
    }

    public List<Discente> ordinaPerNomeDesc() {
        return discenteRepository.findAll()
                .stream()
                .sorted((d1, d2) -> d2.getNomeDiscente().compareToIgnoreCase(d1.getNomeDiscente()))
                .collect(Collectors.toList());
    }

    public List<Discente> trovaDiscentiDaTeramo() {
        return discenteRepository.findAll()
                .stream()
                .filter(d -> "Teramo".equalsIgnoreCase(d.getCittaResidenza()))
                .collect(Collectors.toList());
    }

}
