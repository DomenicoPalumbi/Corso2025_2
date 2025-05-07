package com.example.demo.controller;

import com.example.demo.entity.Discente;
import com.example.demo.service.DiscenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/discenti")
public class DiscenteController {

    @Autowired
    DiscenteService discenteService;

    // LISTA
    @GetMapping("/lista")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("lista-discenti");
        List<Discente> discenti = discenteService.findAllByOrderByIdAsc();
        modelAndView.addObject("discenti", discenti);
        return modelAndView;
    }

    // FORM NUOVO (CREA)
    @GetMapping("/nuovo")
    public String showAdd(ModelAndView model) {
        model.addObject("discente", new Discente());
        return "nuovo-discente";  // Usa form separato per nuovo
    }

    // SALVA NUOVO
    @PostMapping
    public String create(@ModelAttribute("discente") Discente discente, BindingResult br) {
        if (br.hasErrors()) return "nuovo-discente";
        discenteService.save(discente);
        return "redirect:/discenti/lista";
    }

    // FORM EDIT
    @GetMapping("/{id}/edit")
    public ModelAndView showEdit(@PathVariable Long id) {
        Discente discente = discenteService.get(id);
        ModelAndView modelAndView = new ModelAndView("nuovo-discente");
        modelAndView.addObject("discente", discente);
        return modelAndView;
    }

    // AGGIORNA
    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("discente") Discente discente, BindingResult br) {
        if (br.hasErrors()) return "nuovo-discente";
        discente.setId(id);  // Imposta l'ID per l'aggiornamento
        discenteService.save(discente);
        return "redirect:/discenti/lista";
    }

    // DELETE
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        discenteService.delete(id);
        return "redirect:/discenti/lista";
    }
    // GET /discenti/lista-ordinata-asc
    @GetMapping("/asc")
    public ModelAndView listOrderedAsc() {
        ModelAndView modelAndView = new ModelAndView("lista-discenti");
        List<Discente> discenti = discenteService.findAllOrderByNomeAsc();
        modelAndView.addObject("discenti", discenti);
        return modelAndView;
    }

    // GET /discenti/lista-ordinata-desc
    @GetMapping("/desc")
    public ModelAndView listOrderedDesc() {
        ModelAndView modelAndView = new ModelAndView("lista-discenti");
        List<Discente> discenti = discenteService.findAllOrderByNomeDesc();
        modelAndView.addObject("discenti", discenti);
        return modelAndView;
    }
    @GetMapping("/teramo")
    public ModelAndView listByCittaResidenza() {
        ModelAndView modelAndView = new ModelAndView("lista-discenti");
        List<Discente> discenti = discenteService.findByCittaResidenza("Teramo");
        modelAndView.addObject("discenti", discenti);
        return modelAndView;
    }

}
