package com.example.demo.service;

import com.example.demo.entity.Corso;
import com.example.demo.repository.CorsoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorsoService {


    @Autowired
    CorsoRepository corsoRepository;

    public List<Corso> findAll() {
        return corsoRepository.findAll();
    }

    public Corso get(Long id) {
        return corsoRepository.findById(id).orElseThrow();
    }

    public Corso save(Corso c) {
        return corsoRepository.save(c);
    }

    public void delete(Long id) {
        corsoRepository.deleteById(id);
    }
}