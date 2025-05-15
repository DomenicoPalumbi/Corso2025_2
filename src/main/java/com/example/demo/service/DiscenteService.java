package com.example.demo.service;

import com.example.demo.data.dto.DiscenteDTO;
import com.example.demo.data.entity.Discente;
import com.example.demo.repository.DiscenteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    // Metodo per convertire Discente in DiscenteDTO usando ModelMapper
    private DiscenteDTO convertToDTO(Discente discente) {
        return modelMapper.map(discente, DiscenteDTO.class);
    }

    // Metodo per ottenere un Discente dal database tramite ID
    public DiscenteDTO getDiscentiById(Long id) {
        Optional<Discente> discente = discenteRepository.findById(id);
        return discente.map(this::convertToDTO).orElse(null); // Restituisce null se il discente non è trovato
    }

    // Metodo per salvare un nuovo Discente
    public void saveDiscente(DiscenteDTO discenteDTO) {
        Discente discente = modelMapper.map(discenteDTO, Discente.class);
        discenteRepository.save(discente);
    }

    // Metodo per aggiornare un Discente esistente
    public void updateDiscente(Long id, DiscenteDTO discenteDTO) {
        Discente discente = discenteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Discente non trovato con ID: " + id));
        discente.setNome(discenteDTO.getNome());
        discente.setCognome(discenteDTO.getCognome());
        discente.setEta(discenteDTO.getEta());
        discente.setCittaResidenza(discenteDTO.getCittaResidenza());
        discenteRepository.save(discente);
    }

    // Metodo per eliminare un Discente
    public void deleteDiscente(Long id) {
        if (!discenteRepository.existsById(id)) {
            throw new IllegalArgumentException("Discente non trovato con ID: " + id);
        }
        discenteRepository.deleteById(id);
    }

    // Metodo per ottenere tutti i Discenti
    public List<DiscenteDTO> getAllDiscenti() {
        List<Discente> discenti = discenteRepository.findAll();
        return discenti.stream()
                .map(this::convertToDTO)  // Converte ogni Discente in DiscenteDTO
                .collect(Collectors.toList());
    }

    // Metodo per trovare tutti i Discenti ordinati per nome ascendente
    public List<DiscenteDTO> findAllOrderByNomeAsc() {
        return discenteRepository.findAllOrderByNomeAsc()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Metodo per trovare tutti i Discenti ordinati per nome discendente
    public List<DiscenteDTO> findAllOrderByNomeDesc() {
        return discenteRepository.findAllOrderByNomeDesc()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Metodo per trovare Discenti per città di residenza
    public List<DiscenteDTO> findByCittaResidenza(String citta) {
        return discenteRepository.findByCittaResidenza(citta)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    public List<Discente> ordinaPerNomeAsc() {
        return discenteRepository.findAll()
                .stream()
                .sorted((d1, d2) -> d1.getNome().compareToIgnoreCase(d2.getNome()))
                .collect(Collectors.toList());
    }

    // Ordina i discenti per nome in ordine decrescente
    public List<Discente> ordinaPerNomeDesc() {
        return discenteRepository.findAll()
                .stream()
                .sorted((d1, d2) -> d2.getNome().compareToIgnoreCase(d1.getNome()))
                .collect(Collectors.toList());
    }

    // Filtra i discenti in base alla città di residenza (ad esempio, "Teramo")
    public List<Discente> trovaDiscentiDaTeramo() {
        return discenteRepository.findAll()
                .stream()
                .filter(discente -> "Teramo".equalsIgnoreCase(discente.getCittaResidenza()))
                .collect(Collectors.toList());
    }

}
