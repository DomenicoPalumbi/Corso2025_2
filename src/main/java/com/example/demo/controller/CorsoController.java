package com.example.demo.controller;

import com.example.demo.data.dto.*;
import com.example.demo.service.CorsoService;
import com.example.demo.service.DiscenteService;
import com.example.demo.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/corsi")
public class CorsoController {

    @Autowired
    private CorsoService corsoService;

    @Autowired
    private DocenteService docenteService;

    @Autowired
    private DiscenteService discenteService;

    // Mostra lista corsi (usa DTO)
    @GetMapping("/lista")
    public List<CorsoDTO> listaCorsi(){
        return corsoService.getAllCorsiDTO();
    }

    // Salva nuovo corso
    @PostMapping("/salva")
    public CorsoFullDTO saveCorso(@RequestBody CorsoFullDTO corsoFullDTO) {
        return corsoService.saveCorso(corsoFullDTO);
    }

    // Aggiornamento corso
    @PostMapping("/{id}")
    public String updateCorso(@PathVariable Long id, @ModelAttribute("corso") CorsoFullDTO corsoDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("docenti", docenteService.getAllDocenti());
            model.addAttribute("discenti", discenteService.getAllDiscenti());
            return "nuovo-corso";
        }
        corsoService.updateCorso(id, corsoDTO);
        return "redirect:/corsi/lista";
    }

    // Elimina corso
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        corsoService.deleteCorso(id);
        return "redirect:/corsi/lista";
    }
}
