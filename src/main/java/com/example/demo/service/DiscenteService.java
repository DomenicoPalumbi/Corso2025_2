package com.example.demo.service;

import com.example.demo.data.dto.DiscenteDTO;
import com.example.demo.data.dto.DiscenteFullDTO;
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

    // Convertitore in DTO base
    private DiscenteDTO convertToDTO(Discente discente) {
        return modelMapper.map(discente, DiscenteDTO.class);
    }

    // Convertitore in FullDTO
    private DiscenteFullDTO convertToFullDTO(Discente discente) {
        return new DiscenteFullDTO(discente);
    }

    // Ottenere DTO base
    public DiscenteDTO getDiscentiById(Long id) {
        Optional<Discente> discente = discenteRepository.findById(id);
        return discente.map(this::convertToDTO).orElse(null);
    }

    // Ottenere FullDTO per la modifica
    public DiscenteFullDTO getFullDiscenteById(Long id) {
        Optional<Discente> discente = discenteRepository.findById(id);
        return discente.map(this::convertToFullDTO).orElse(null);
    }

    // Salvataggio nuovo Discente (usa FullDTO per includere matricola ecc.)
    public void saveDiscente(DiscenteFullDTO dto) {
        Discente discente = new Discente();
        discente.setNome(dto.getNome());
        discente.setCognome(dto.getCognome());
        discente.setMatricola(dto.getMatricola());
        discente.setEta(dto.getEta());
        discente.setCittaResidenza(dto.getCittaResidenza());
        discenteRepository.save(discente);
    }

    // Aggiornamento Discente esistente (da FullDTO)
    public void updateDiscente(Long id, DiscenteFullDTO dto) {
        Discente discente = discenteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Discente non trovato con ID: " + id));

        discente.setNome(dto.getNome());
        discente.setCognome(dto.getCognome());
        discente.setMatricola(dto.getMatricola());
        discente.setEta(dto.getEta());
        discente.setCittaResidenza(dto.getCittaResidenza());

        discenteRepository.save(discente);
    }

    public void deleteDiscente(Long id) {
        if (!discenteRepository.existsById(id)) {
            throw new IllegalArgumentException("Discente non trovato con ID: " + id);
        }
        discenteRepository.deleteById(id);
    }

    public List<DiscenteDTO> getAllDiscenti() {
        return discenteRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<DiscenteDTO> findAllOrderByNomeAsc() {
        return discenteRepository.findAllOrderByNomeAsc()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<DiscenteDTO> findAllOrderByNomeDesc() {
        return discenteRepository.findAllOrderByNomeDesc()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

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

    public List<Discente> ordinaPerNomeDesc() {
        return discenteRepository.findAll()
                .stream()
                .sorted((d1, d2) -> d2.getNome().compareToIgnoreCase(d1.getNome()))
                .collect(Collectors.toList());
    }

    public List<Discente> trovaDiscentiDaTeramo() {
        return discenteRepository.findAll()
                .stream()
                .filter(d -> "Teramo".equalsIgnoreCase(d.getCittaResidenza()))
                .collect(Collectors.toList());
    }
}
