package com.example.demo.controller;

import com.example.demo.data.dto.DiscenteDTO;
import com.example.demo.data.dto.DiscenteFullDTO;
import com.example.demo.data.dto.DocenteDTO;
import com.example.demo.service.DiscenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discenti")
public class DiscenteController {

    @Autowired
    private DiscenteService discenteService;

    @GetMapping("/lista")
    public ResponseEntity<List<DiscenteDTO>> listaDiscenti() {
        List<DiscenteDTO> discenti = discenteService.getAllDiscenti();
        return ResponseEntity.ok(discenti);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DiscenteDTO> getDiscenteById(@PathVariable Long id) {
        DiscenteDTO discente = discenteService.getDiscenteById(id);
        return ResponseEntity.ok(discente);
    }
    @PostMapping("/nuovo")
    public ResponseEntity<DiscenteDTO> saveDiscente(@RequestBody DiscenteDTO discenteDTO) {
        DiscenteDTO savedDiscente = discenteService.saveDiscente(discenteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDiscente);
    }
    @PostMapping("/discenti/by-ids")
    public List<DiscenteDTO> getDiscentiByIds(@RequestBody List<Long> ids) {
        return discenteService.getDiscentiByIds(ids);
    }


    @PutMapping("/{id}")
    public ResponseEntity<DiscenteDTO> updateDiscente(
            @PathVariable Long id,
            @RequestBody DiscenteDTO discenteDTO) {
        DiscenteDTO updatedDiscente = discenteService.updateDiscente(id, discenteDTO);
        return ResponseEntity.ok(updatedDiscente);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDiscente(@PathVariable Long id) {
        discenteService.deleteDiscente(id);
        return ResponseEntity.noContent().build();
    }

}

