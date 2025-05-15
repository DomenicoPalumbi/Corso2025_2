package com.example.demo.controller;

import com.example.demo.data.dto.DiscenteDTO;
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

    @GetMapping("/lista")
    public String list(Model model) {
        List<DiscenteDTO> discentiDTO = discenteService.getAllDiscenti();
        model.addAttribute("discenti", discentiDTO);
        return "lista-discenti";
    }

    @GetMapping("/asc")
    public String listAsc(Model model) {
        List<DiscenteDTO> discentiDTO = discenteService.findAllOrderByNomeAsc();
        model.addAttribute("discenti", discentiDTO);
        return "lista-discenti";
    }

    @GetMapping("/desc")
    public String listDesc(Model model) {
        List<DiscenteDTO> discentiDTO = discenteService.findAllOrderByNomeDesc();
        model.addAttribute("discenti", discentiDTO);
        return "lista-discenti";
    }

    @GetMapping("/teramo")
    public String listTeramo(Model model) {
        List<DiscenteDTO> discentiDTO = discenteService.findByCittaResidenza("Teramo");
        model.addAttribute("discenti", discentiDTO);
        return "lista-discenti";
    }

    @GetMapping("/nuovo")
    public String showAdd(Model model) {
        model.addAttribute("discente", new DiscenteDTO());
        return "nuovo-discente";
    }

    @PostMapping("/nuovo")
    public String create(@ModelAttribute("discente") DiscenteDTO discenteDTO, BindingResult result) {
        discenteService.saveDiscente(discenteDTO);
        return "redirect:/discenti/lista";
    }

    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        DiscenteDTO discenteDTO = discenteService.getDiscentiById(id);
        if (discenteDTO == null) {
            return "redirect:/discenti/lista";
        }
        model.addAttribute("discente", discenteDTO);
        return "nuovo-discente";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute("discente") DiscenteDTO discenteDTO, BindingResult result) {
        discenteService.updateDiscente(id, discenteDTO);
        return "redirect:/discenti/lista";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        discenteService.deleteDiscente(id);
        return "redirect:/discenti/lista";
    }
}
