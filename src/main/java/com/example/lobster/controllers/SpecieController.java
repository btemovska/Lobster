package com.example.lobster.controllers;

import com.example.lobster.model.Specie;
import com.example.lobster.repositories.SpecieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
public class SpecieController {

    @Resource
    private SpecieRepository specieRepo;

    @RequestMapping({"/species", "/", ""})
    public String displaySpecies(Model model){
        model.addAttribute("species", specieRepo.findAll());
        return "speciesView";
    }

    @GetMapping("/species/{type}")
    public String displaySingleSpecie(@PathVariable String type, Model model){
        Specie retrievedSpecie = specieRepo.findSpecieByType(type);
        model.addAttribute("specie", retrievedSpecie);
        return "specieView";
    }

    @PostMapping("/add-specie")
    public String addSpecie(@RequestParam String type) {
        Specie specieToAdd = specieRepo.findSpecieByType(type);
        if(specieToAdd == null) {
            specieRepo.save(new Specie(type));
        }
        return "redirect:species";
    }

}
