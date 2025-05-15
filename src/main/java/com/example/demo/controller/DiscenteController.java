package com.example.demo.controller;

import com.example.demo.data.dto.DiscenteDTO;
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
        return "lista-discenti";  // La vista per la lista degli studenti
    }

    // Mostra il form per aggiungere un nuovo discente
    @GetMapping("/nuovo")
    public String showAdd(Model model) {
        model.addAttribute("discente", new DiscenteDTO());
        return "nuovo-discente";  // La vista per il form di aggiunta
    }

    // Salva il nuovo discente
    @PostMapping("/nuovo")
    public String create(@ModelAttribute("discente") DiscenteDTO discenteDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "nuovo-discente";  // Ritorna alla form se ci sono errori
        }
        discenteService.saveDiscente(discenteDTO);
        return "redirect:/discenti/lista";  // Redirige alla lista degli studenti
    }

    // Mostra il form per modificare un discente esistente
    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        DiscenteDTO discenteDTO = discenteService.getDiscentiById(id);
        if (discenteDTO == null) {
            return "redirect:/discenti/lista";  // Se il discente non esiste, redirige alla lista
        }
        model.addAttribute("discente", discenteDTO);
        return "nuovo-discente";  // La vista per il form di modifica
    }

    // Aggiorna un discente
    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute("discente") DiscenteDTO discenteDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "nuovo-discente";  // Ritorna alla form se ci sono errori
        }
        discenteService.updateDiscente(id, discenteDTO);
        return "redirect:/discenti/lista";  // Redirige alla lista degli studenti
    }

    // Elimina un discente
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        discenteService.deleteDiscente(id);
        return "redirect:/discenti/lista";  // Redirige alla lista degli studenti
    }
        @GetMapping("/asc")
        public String ordinaPerNomeAsc(Model model) {
            List<Discente> discentiOrdinati = discenteService.ordinaPerNomeAsc();
            model.addAttribute("discenti", discentiOrdinati);
            return "lista-discenti"; // Ad esempio "elencoDiscenti.jsp"
        }

        @GetMapping("/desc")
        public String ordinaPerNomeDesc(Model model) {
            List<Discente> discentiOrdinati = discenteService.ordinaPerNomeDesc();
            model.addAttribute("discenti", discentiOrdinati);
            return "lista-discenti";
        }

        @GetMapping("/teramo")
        public String filtraPerCittaTeramo(Model model) {
            List<Discente> discentiTeramo = discenteService.trovaDiscentiDaTeramo();
            model.addAttribute("discenti", discentiTeramo);
            return "lista-discenti";
        }
    }

