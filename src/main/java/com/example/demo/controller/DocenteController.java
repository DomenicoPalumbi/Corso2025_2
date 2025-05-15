package com.example.demo.controller;

import com.example.demo.data.dto.DocenteDTO;
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
        model.addAttribute("docente", new DocenteDTO());
        return "form-docente";
    }

    // Salva il nuovo docente
    @PostMapping("/nuovo")
    public String create( @ModelAttribute("docente") DocenteDTO docenteDTO, BindingResult result) {
       // if (result.hasErrors()) {
         //   return "form-docente";  // Ritorna alla form se ci sono errori
       // }
        docenteService.saveDocente(docenteDTO);
        return "redirect:/docenti/lista";  // Redirige alla lista dei docenti
    }

    // Modifica docente
    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        DocenteDTO docenteDTO = docenteService.getDocenteById(id);
        if (docenteDTO == null) {
            return "redirect:/docenti/lista";  // Se il docente non esiste, redirige alla lista
        }
        model.addAttribute("docente", docenteDTO);
        return "form-docente";
    }

    // Aggiorna docente
    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute("docente") DocenteDTO docenteDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "form-docente";  // Ritorna alla form se ci sono errori
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
