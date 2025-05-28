package com.example.demo.controller;

import com.example.demo.data.dto.DocenteDTO;
import com.example.demo.data.dto.DocenteFullDTO;
import com.example.demo.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/docenti")
public class DocenteController {

    @Autowired
    private DocenteService docenteService;
    // Lista dei docenti
    @GetMapping("/lista")
    public List<DocenteDTO> listaDocenti(){
        return docenteService.getAllDocenti();
    }

    // Aggiungere un nuovo docente
    @PostMapping("/nuovo")
    public DocenteFullDTO saveDocente(@RequestBody DocenteFullDTO docenteFullDTO) {
        return docenteService.saveDocente(docenteFullDTO);
    }

    // Aggiorna docente
    // Update a product
    @PutMapping("/{id}")
    public ResponseEntity<DocenteDTO> updateDocente(@PathVariable Long id, @RequestBody DocenteFullDTO docenteFullDTO) {
        DocenteDTO updateDocente = docenteService.updateDocente(id, docenteFullDTO);
        return ResponseEntity.ok(updateDocente);
    }
    // Elimina docente
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDocente(@PathVariable Long id) {
        docenteService.deleteDocente(id);
        return ResponseEntity.noContent().build();
    }
    //commento prova
}
