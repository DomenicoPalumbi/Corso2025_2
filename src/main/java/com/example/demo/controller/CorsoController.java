package com.example.demo.controller;

import com.example.demo.entity.Corso;
import com.example.demo.service.CorsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/corsi")
public class CorsoController {

    @Autowired
    CorsoService corsoService;

    // LISTA
    @GetMapping("/lista")
    public String list(Model model) {
        List<Corso> corsi = corsoService.findAll();
        model.addAttribute("corsi", corsi);
        return "list-corsi";
    }

    // FORM NUOVO (CREA)
    @GetMapping("/nuovo")
    public String showAdd(Model model) {
        model.addAttribute("corso", new Corso());
        return "nuovo-corso";  // Usa form separato per nuovo
    }

    // SALVA NUOVO
    @PostMapping
    public String create(@ModelAttribute("corso") Corso corso, BindingResult br) {
        if (br.hasErrors()) return "nuovo-corso";
        corsoService.save(corso);
        return "redirect:/corsi/lista";
    }

    // FORM EDIT
    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        Corso corso = corsoService.get(id);
        model.addAttribute("corso", corso);
        return "nuovo-corso";  // Usa form separato per modifica
    }

    // AGGIORNA
    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("corso") Corso corso, BindingResult br) {
        if (br.hasErrors()) return "nuovo-corso";
        corso.setId(id);  // Imposta l'ID per l'aggiornamento
        corsoService.save(corso);
        return "redirect:/corsi/lista";
    }

    // DELETE
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        corsoService.delete(id);
        return "redirect:/corsi/lista";
    }
}
