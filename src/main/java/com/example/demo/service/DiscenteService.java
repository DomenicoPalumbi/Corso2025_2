package com.example.demo.service;

import com.example.demo.entity.Discente;
import com.example.demo.repository.DiscenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscenteService {


    @Autowired
    DiscenteRepository discenteRepository;

    public List<Discente> findAllByOrderByIdAsc() {
        return discenteRepository.findAllByOrderByIdAsc();
    }

    public Discente get(Long id) {
        return discenteRepository.findById(id).orElseThrow();
    }

    public Discente save(Discente d) {
        return discenteRepository.save(d);
    }

    public List<Discente> findAllOrderByNomeAsc() {
        return discenteRepository.findAllOrderByNomeAsc();  // Chiamata automatica
    }
    public List<Discente> findAllOrderByNomeDesc() {
        return discenteRepository.findAllOrderByNomeDesc();  // Chiamata automatica
    }
    public List<Discente> findByCittaResidenza(String cittaResidenza) {
        return discenteRepository.findByCittaResidenza(cittaResidenza);
    }

    public void delete(Long id) {
        discenteRepository.deleteById(id);
    }
}
