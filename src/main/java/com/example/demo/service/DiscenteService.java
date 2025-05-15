package com.example.demo.service;

import com.example.demo.converter.Converter;
import com.example.demo.data.dto.DiscenteDTO;
import com.example.demo.data.entity.Discente;
import com.example.demo.repository.DiscenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiscenteService {

    @Autowired
    private DiscenteRepository discenteRepository;

    /*public List<DiscenteDTO> getAllDiscenti() {
        List<Discente> discenti = discenteRepository.findAll();
        return discenti.stream()
                .map(Converter::toDTO)
                .collect(Collectors.toList());
    }*/

    public DiscenteDTO getDiscentiById(Long id) {
        Optional<Discente> discente = discenteRepository.findById(id);
        return discente.map(Converter::toDTO).orElse(null);
    }

    public void saveDiscente(DiscenteDTO discenteDTO) {
        Discente discente = Converter.toEntity(discenteDTO);
        discenteRepository.save(discente);
    }

    public void updateDiscente(Long id, DiscenteDTO discenteDTO) {
        Discente discente = discenteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Discente non trovato"));
        discente.setNome(discenteDTO.getNome());
        discente.setCognome(discenteDTO.getCognome());
        discente.setEta(discenteDTO.getEta());
        discente.setCittaResidenza(discenteDTO.getCittaResidenza());
        discenteRepository.save(discente);
    }

    public void deleteDiscente(Long id) {
        discenteRepository.deleteById(id);
    }

    public List<DiscenteDTO> findAllOrderByNomeAsc() {
        return discenteRepository.findAllOrderByNomeAsc()
                .stream().map(Converter::toDTO).collect(Collectors.toList());
    }

    public List<DiscenteDTO> findAllOrderByNomeDesc() {
        return discenteRepository.findAllOrderByNomeDesc()
                .stream().map(Converter::toDTO).collect(Collectors.toList());
    }

    public List<DiscenteDTO> findByCittaResidenza(String citta) {
        return discenteRepository.findByCittaResidenza(citta)
                .stream().map(Converter::toDTO).collect(Collectors.toList());
    }

    public List<DiscenteDTO> getAllDiscenti() {
        List<Discente> discenti = discenteRepository.findAll();
        return discenti.stream()
                .map(Converter::toDTO)  // Converte ogni Discente in DiscenteDTO
                .collect(Collectors.toList());
    }
}
