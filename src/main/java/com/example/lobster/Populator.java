package com.example.lobster;

import com.example.lobster.model.Lobster;
import com.example.lobster.model.Specie;
import com.example.lobster.repositories.LobsterRepository;
import com.example.lobster.repositories.SpecieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Populator implements CommandLineRunner {

    @Resource
    private SpecieRepository specieRepo;
    @Resource
    private LobsterRepository lobsterRepo;

    @Override
    public void run(String... args) throws Exception{
        Specie clawed = new Specie("Clawed");
        Specie spiny = new Specie("Spiny");
        specieRepo.save(clawed);
        specieRepo.save(spiny);

        Lobster reefLobster = new Lobster("Reef Lobster", "description goes here", clawed);
        Lobster squadLobster = new Lobster("Squat Lobster", "description goes here", clawed);
        Lobster americanLobster = new Lobster("American Lobster", "description goes here", clawed);
        Lobster europeanLobster = new Lobster("European Lobster", "description goes here", clawed);
        Lobster rockLobster = new Lobster("Rock Lobster", "description goes here", spiny);
        Lobster slipperLobster = new Lobster("Slipper Lobster", "description goes here", spiny);
        Lobster furryLobster = new Lobster("Furry Lobster", "description goes here", spiny);
        lobsterRepo.save(reefLobster);
        lobsterRepo.save(squadLobster);
        lobsterRepo.save(americanLobster);
        lobsterRepo.save(europeanLobster);
        lobsterRepo.save(rockLobster);
        lobsterRepo.save(slipperLobster);
        lobsterRepo.save(furryLobster);
    }
}
