package com.example.demo.controller;

import com.example.demo.entity.Docente;
import com.example.demo.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/docenti")
public class DocenteController {

    @Autowired
    DocenteService docenteService;

    // LISTA
    @GetMapping("/lista")
    public String list(Model model) {
        List<Docente> docenti = docenteService.findAll();
        model.addAttribute("docenti", docenti);
        return "list-docenti";
    }

    // FORM NUOVO (CREA)
    @GetMapping("/nuovo")
    public String showAdd(Model model) {
        model.addAttribute("docente", new Docente());
        return "form-nuovo-docente";  // Usa form separato per nuovo
    }

    // SALVA NUOVO
    @PostMapping
    public String create(@ModelAttribute("docente") Docente docente, BindingResult br) {
        if (br.hasErrors()) return "form-nuovo-docente";
        docenteService.save(docente);
        return "redirect:/docenti/lista";
    }

    // FORM EDIT
    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        Docente docente = docenteService.get(id);
        model.addAttribute("docente", docente);
        return "form-modifica-docente";  // Usa form separato per modifica
    }

    // AGGIORNA
    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("docente") Docente docente, BindingResult br) {
        if (br.hasErrors()) return "form-modifica-docente";
        docente.setId(id);  // Imposta l'ID per l'aggiornamento
        docenteService.save(docente);
        return "redirect:/docenti/lista";
    }

    // DELETE
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        docenteService.delete(id);
        return "redirect:/docenti/lista";
    }
}
