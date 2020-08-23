package com.example.lobster.rest.controllers;

import com.example.lobster.model.Specie;
import com.example.lobster.repositories.SpecieRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;

@RestController
public class SpecieRestController {

    @Resource
    private SpecieRepository specieRepo;

    @GetMapping("/api/species")
    public Collection<Specie> getSpecies() {
        return (Collection<Specie>) specieRepo.findAll();
    }

    @GetMapping("/api/species/{specieId}")
    public Specie getSpecie(@PathVariable Long specieId){
        return specieRepo.findById(specieId).get();
    }
}
