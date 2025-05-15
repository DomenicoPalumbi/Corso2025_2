package com.example.demo.controller;

import com.example.demo.converter.CorsoMapper;
import com.example.demo.data.dto.CorsoDTO;
import com.example.demo.data.dto.DiscenteDTO;
import com.example.demo.data.dto.DocenteDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.service.CorsoService;
import com.example.demo.service.DiscenteService;
import com.example.demo.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/corsi")
public class CorsoController {

    @Autowired
    private CorsoService corsoService;

    @Autowired
    private DocenteService docenteService;

    @Autowired
    private DiscenteService discenteService;

    @Autowired
    private CorsoMapper corsoMapper;

    @GetMapping("/lista")
    public String lista(Model model) {
        List<CorsoDTO> corsi = corsoService.findAll().stream()
                .map(corsoMapper::toDTO)
                .toList();

        // Creazione delle mappe per docenti e discenti
        Map<Long, DocenteDTO> docentiMap = docenteService.getAllDocenti().stream()
                .collect(Collectors.toMap(DocenteDTO::getId, docente -> docente));
        Map<Long, DiscenteDTO> discentiMap = discenteService.getAllDiscenti().stream()
                .collect(Collectors.toMap(DiscenteDTO::getId, discente -> discente));

        // Log per verificare i dati
        System.out.println("Docenti Map: " + docentiMap);
        System.out.println("Discenti Map: " + discentiMap);

        model.addAttribute("corsi", corsi);
        model.addAttribute("docentiMap", docentiMap);
        model.addAttribute("discentiMap", discentiMap);

        return "list-corsi";
    }

    // GET /corsi/nuovo – Form inserimento
    @GetMapping("/nuovo")
    public String nuovo(Model model) {
        model.addAttribute("corso", new CorsoDTO());
        model.addAttribute("docenti", docenteService.getAllDocenti());
        model.addAttribute("discenti", discenteService.getAllDiscenti());
        return "nuovo-corso";
    }

    @PostMapping("/salva")
    public String salva(@ModelAttribute("corso") CorsoDTO corsoDTO) {
        System.out.println("ID Corso: " + corsoDTO.getId());  // Log per verificare se l'ID è passato correttamente
        Corso corso = corsoMapper.toEntity(corsoDTO);  // Converte il DTO in entità Corso

        if (corso.getId() == null) {
            corsoService.save(corso);  // Salva un nuovo corso
        } else {
            corsoService.update(corso);  // Modifica un corso esistente
        }

        return "redirect:/corsi/lista";  // Redirect alla lista dei corsi dopo il salvataggio
    }

    @GetMapping("/{id}/edit")
    public String modifica(@PathVariable Long id, Model model) {
        Corso corso = corsoService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Corso non trovato"));
        CorsoDTO dto = corsoMapper.toDTO(corso);

        model.addAttribute("corso", dto);
        model.addAttribute("docenti", docenteService.getAllDocenti());
        model.addAttribute("discenti", discenteService.getAllDiscenti());

        return "nuovo-corso";  // Nome della JSP per la modifica
    }

    @GetMapping("/{id}/delete")
    public String elimina(@PathVariable Long id) {
        if (corsoService.existsById(id)) {
            corsoService.deleteById(id);
        }
        return "redirect:/corsi/lista";
    }

}
