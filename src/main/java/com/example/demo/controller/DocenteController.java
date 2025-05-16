package com.example.demo.controller;

import com.example.demo.data.dto.DocenteDTO;
import com.example.demo.data.dto.DocenteFullDTO;
import com.example.demo.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/docenti")
public class DocenteController {

    @Autowired
    private DocenteService docenteService;
    // Lista dei docenti
    @GetMapping("/lista")
    public String list(Model model) {
        List<DocenteDTO> docentiDTO = docenteService.getAllDocenti();
        model.addAttribute("docenti", docentiDTO);
        return "list-docenti";
    }

    // Mostra il form per aggiungere un nuovo docente
    @GetMapping("/nuovo")
    public String showAdd(Model model) {
        model.addAttribute("docente", new DocenteFullDTO());
        return "form-docente";
    }

    // Salva il nuovo docente
    @PostMapping("/nuovo")
    public String create(@ModelAttribute("docente") DocenteFullDTO docenteFullDTO, BindingResult result, Model model) {
        // Validazione manuale
        if (docenteFullDTO.getNome() == null || docenteFullDTO.getNome().isEmpty()) {
            result.rejectValue("nome", "nome.empty", "Il nome non può essere vuoto.");
        }
        if (docenteFullDTO.getCognome() == null || docenteFullDTO.getCognome().isEmpty()) {
            result.rejectValue("cognome", "cognome.empty", "Il cognome non può essere vuoto.");
        }
        if (docenteFullDTO.getEmail() == null || docenteFullDTO.getEmail().isEmpty()) {
            result.rejectValue("email", "email.empty", "L'email non può essere vuota.");
        }

        // Se ci sono errori, ritorna alla form
        if (result.hasErrors()) {
            return "form-docente";
        }

        docenteService.saveDocente(docenteFullDTO);
        return "redirect:/docenti/lista";  // Redirige alla lista dei docenti
    }

    // Mostra il form per modificare un docente esistente
    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        DocenteFullDTO docenteDTO = docenteService.getFullDocenteById(id);
        if (docenteDTO == null) {
            return "redirect:/docenti/lista";  // Se il docente non esiste, redirige alla lista
        }
        model.addAttribute("docente", docenteDTO);
        return "form-docente";
    }

    // Aggiorna docente
    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute("docente") DocenteFullDTO docenteDTO, BindingResult result, Model model) {
        // Validazione manuale
        if (docenteDTO.getNome() == null || docenteDTO.getNome().isEmpty()) {
            result.rejectValue("nome", "nome.empty", "Il nome non può essere vuoto.");
        }
        if (docenteDTO.getCognome() == null || docenteDTO.getCognome().isEmpty()) {
            result.rejectValue("cognome", "cognome.empty", "Il cognome non può essere vuoto.");
        }
        if (docenteDTO.getEmail() == null || docenteDTO.getEmail().isEmpty()) {
            result.rejectValue("email", "email.empty", "L'email non può essere vuota.");
        }

        // Se ci sono errori, ritorna alla form
        if (result.hasErrors()) {
            return "form-docente";
        }

        docenteService.updateDocente(id, docenteDTO);
        return "redirect:/docenti/lista";  // Redirige alla lista dei docenti
    }

    // Elimina docente
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        docenteService.deleteDocente(id);
        return "redirect:/docenti/lista";  // Redirige alla lista dei docenti
    }
}
