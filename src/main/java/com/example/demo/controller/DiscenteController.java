package com.example.demo.controller;

import com.example.demo.data.dto.DiscenteDTO;
import com.example.demo.data.dto.DiscenteFullDTO;
import com.example.demo.data.dto.DocenteDTO;
import com.example.demo.data.entity.Discente;
import com.example.demo.repository.DiscenteRepository;
import com.example.demo.service.DiscenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/discenti")
public class DiscenteController {

    @Autowired
    private DiscenteService discenteService;
    @Autowired
    private DiscenteRepository discenteRepository;

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
    @PostMapping("/by-ids")
    public List<DiscenteDTO> getDiscentiByIds(@RequestBody List<Long> ids) {
        return discenteService.getDiscentiByIds(ids);
    }
    @PostMapping("/get-or-create")
    public ResponseEntity<DiscenteDTO> getOrCreateDiscente(@RequestBody DiscenteDTO input) {
        Discente discente = discenteRepository.findByNomeDiscenteAndCognomeDiscente(
                input.getNomeDiscente(),
                input.getCognomeDiscente()
        );

        if (discente == null) {
            discente = new Discente();
            discente.setNomeDiscente(input.getNomeDiscente());
            discente.setCognomeDiscente(input.getCognomeDiscente());
            discente = discenteRepository.save(discente);
        }

        return ResponseEntity.ok(new DiscenteDTO(discente));
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

