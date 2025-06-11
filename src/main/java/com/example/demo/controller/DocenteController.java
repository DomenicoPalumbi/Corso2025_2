package com.example.demo.controller;

import com.example.demo.data.dto.DocenteDTO;
import com.example.demo.data.dto.DocenteFullDTO;
import com.example.demo.data.entity.Docente;
import com.example.demo.repository.DocenteRepository;
import com.example.demo.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/docenti")
public class DocenteController {

    @Autowired
    private DocenteService docenteService;
    @Autowired
    private DocenteRepository docenteRepository;
    // Lista dei docenti
    @GetMapping("/lista")
    public List<DocenteDTO> listaDocenti(){
        return docenteService.getAllDocenti();
    }
    @GetMapping("/{id}")
    public ResponseEntity<DocenteDTO> getDocenteById(@PathVariable Long id) {
        DocenteDTO docente = docenteService.getDocenteById(id);
        return ResponseEntity.ok(docente);
    }

    // Aggiungere un nuovo docente
    @PostMapping("/nuovo")
    public ResponseEntity<DocenteDTO> creaDocente(@RequestBody DocenteDTO docenteDTO) {
        Docente docente = new Docente();
        docente.setNomeDocente(docenteDTO.getNomeDocente());
        docente.setCognomeDocente(docenteDTO.getCognomeDocente());
        docente.setEmailDocente(docenteDTO.getEmailDocente());

        Docente saved = docenteRepository.save(docente);
        return ResponseEntity.ok(new DocenteDTO(saved));
    }

    // Aggiorna docente
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
    @PostMapping("/cerca")
    public ResponseEntity<DocenteDTO> cercaDocente(@RequestBody Map<String, String> params) {
        String nome = params.get("nome");
        String cognome = params.get("cognome");

        DocenteDTO docente = docenteService.findByNomeAndCognome(nome, cognome);
        if (docente != null) {
            return ResponseEntity.ok(docente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
