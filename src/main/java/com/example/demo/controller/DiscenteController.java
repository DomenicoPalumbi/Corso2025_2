package com.example.demo.controller;

import com.example.demo.data.dto.DiscenteDTO;
import com.example.demo.data.dto.DiscenteFullDTO;
import com.example.demo.data.dto.DiscenteDTO;
import com.example.demo.data.dto.DiscenteFullDTO;
import com.example.demo.data.entity.Discente;
import com.example.demo.service.DiscenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discenti")
public class DiscenteController {

    @Autowired
    private DiscenteService discenteService;

    // Lista degli studenti
    @GetMapping("/lista")
    public List<DiscenteDTO> listaDiscenti(){
        return discenteService.getAllDiscenti();
    }
    // Salva il nuovo discente
    @PostMapping("/nuovo")
    public DiscenteFullDTO saveDiscente(@RequestBody DiscenteFullDTO discenteFullDTO) {
        return discenteService.saveDiscente(discenteFullDTO);
    }

    // Aggiorna un discente
    @PutMapping("/{id}")
    public ResponseEntity<DiscenteDTO> updateDiscente(@PathVariable Long id, @RequestBody DiscenteFullDTO discenteFullDTO) {
        DiscenteDTO updateDiscente = discenteService.updateDiscente(id, discenteFullDTO);
        return ResponseEntity.ok(updateDiscente);
    }

    // Elimina un discente
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDiscente(@PathVariable Long id) {
        discenteService.deleteDiscente(id);
        return ResponseEntity.noContent().build();
    }
}
