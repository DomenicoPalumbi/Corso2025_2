package com.example.demo.controller;

import com.example.demo.data.dto.DiscenteDTO;
import com.example.demo.data.dto.DiscenteFullDTO;
import com.example.demo.data.entity.Discente;
import com.example.demo.service.DiscenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/discenti")
public class DiscenteController {

    @Autowired
    private DiscenteService discenteService;

    // Lista degli studenti
    @GetMapping("/lista")
    public String list(Model model) {
        List<DiscenteDTO> discentiDTO = discenteService.getAllDiscenti();
        model.addAttribute("discenti", discentiDTO);
        return "lista-discenti";
    }

    // Mostra il form per aggiungere un nuovo discente
    @GetMapping("/nuovo")
    public String showAdd(Model model) {
        model.addAttribute("discente", new DiscenteFullDTO());
        return "nuovo-discente";
    }

    // Salva il nuovo discente
    @PostMapping("/nuovo")
    public String create(@ModelAttribute("discente") DiscenteFullDTO discenteDTO, BindingResult result) {
        // Validazione manuale
        if (discenteDTO.getNome() == null || discenteDTO.getNome().isEmpty()) {
            result.rejectValue("nome", "nome.empty", "Il nome non può essere vuoto.");
        }
        if (discenteDTO.getCognome() == null || discenteDTO.getCognome().isEmpty()) {
            result.rejectValue("cognome", "cognome.empty", "Il cognome non può essere vuoto.");
        }
        if (discenteDTO.getMatricola() == null) {
            result.rejectValue("matricola", "matricola.empty", "La matricola è obbligatoria.");
        }

        if (result.hasErrors()) {
            return "nuovo-discente";
        }

        discenteService.saveDiscente(discenteDTO);
        return "redirect:/discenti/lista";
    }

    // Mostra il form per modificare un discente esistente
    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        DiscenteFullDTO discenteDTO = discenteService.getFullDiscenteById(id);
        if (discenteDTO == null) {
            return "redirect:/discenti/lista";
        }
        model.addAttribute("discente", discenteDTO);
        return "nuovo-discente";
    }

    // Aggiorna un discente
    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute("discente") DiscenteFullDTO discenteDTO, BindingResult result) {
        if (discenteDTO.getNome() == null || discenteDTO.getNome().isEmpty()) {
            result.rejectValue("nome", "nome.empty", "Il nome non può essere vuoto.");
        }
        if (discenteDTO.getCognome() == null || discenteDTO.getCognome().isEmpty()) {
            result.rejectValue("cognome", "cognome.empty", "Il cognome non può essere vuoto.");
        }
        if (discenteDTO.getMatricola() == null) {
            result.rejectValue("matricola", "matricola.empty", "La matricola è obbligatoria.");
        }

        if (result.hasErrors()) {
            return "nuovo-discente";
        }

        discenteService.updateDiscente(id, discenteDTO);
        return "redirect:/discenti/lista";
    }

    // Elimina un discente
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        discenteService.deleteDiscente(id);
        return "redirect:/discenti/lista";
    }

    // Ordina i discenti per nome ascendente
    @GetMapping("/asc")
    public String ordinaPerNomeAsc(Model model) {
        List<Discente> discentiOrdinati = discenteService.ordinaPerNomeAsc();
        model.addAttribute("discenti", discentiOrdinati);
        return "lista-discenti";
    }

    // Ordina i discenti per nome discendente
    @GetMapping("/desc")
    public String ordinaPerNomeDesc(Model model) {
        List<Discente> discentiOrdinati = discenteService.ordinaPerNomeDesc();
        model.addAttribute("discenti", discentiOrdinati);
        return "lista-discenti";
    }

    // Filtra i discenti per città = Teramo
    @GetMapping("/teramo")
    public String filtraPerCittaTeramo(Model model) {
        List<Discente> discentiTeramo = discenteService.trovaDiscentiDaTeramo();
        model.addAttribute("discenti", discentiTeramo);
        return "lista-discenti";
    }
}
