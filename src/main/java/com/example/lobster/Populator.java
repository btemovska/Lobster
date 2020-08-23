package com.example.lobster;

import com.example.lobster.model.HashTag;
import com.example.lobster.model.Lobster;
import com.example.lobster.model.Specie;
import com.example.lobster.repositories.HashTagRepository;
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
    @Resource
    private HashTagRepository hashTagRepo;

    @Override
    public void run(String... args) throws Exception{

        HashTag seaFood = new HashTag("Sea Food");
        HashTag crab = new HashTag("Grab");
        hashTagRepo.save(seaFood);
        hashTagRepo.save(crab);

        Specie clawed = new Specie("Clawed");
        Specie spiny = new Specie("Spiny");
        specieRepo.save(clawed);
        specieRepo.save(spiny);

        Lobster reefLobster = new Lobster("Reef Lobster", "Small lobsters that live on reefs in the Indo-Pacific, Caribbean and warmer parts of the Atlantic Ocean", "/images/reefLobster.jpg" , clawed);
        Lobster squadLobster = new Lobster("Squat Lobster", "The majority of squat lobsters are benthic, spending their lives on the sea-floor.", "/images/squadLobster.jpg", clawed);
        Lobster americanLobster = new Lobster("American Lobster", "Also known as Atlantic lobster, Canadian lobster, true lobster, northern lobster, Canadian Reds,[3] or Maine lobster.", "/images/americanLobster.jpg",  clawed);
        Lobster europeanLobster = new Lobster("European Lobster", "Found in eastern Atlantic Ocean, Mediterranean Sea and parts of the Black Sea. It is closely related to the American lobster,", "/images/europeanLobster.jpg",  clawed);
        Lobster rockLobster = new Lobster("Rock Lobster", "Maximum total body length is 58 cm (males), and 43 cm (females)", "/images/rockLobster.jpg", spiny);
        Lobster slipperLobster = new Lobster("Slipper Lobster", "Found in all warm oceans and seas. They are not true lobsters",  "/images/splipperLobster.jpg", spiny);
        Lobster furryLobster = new Lobster("Furry Lobster", "Also called the coral lobster. Closely related to both the slipper lobster and the spiny lobster", "/images/furryLobster.jpg", spiny);
        lobsterRepo.save(reefLobster);
        lobsterRepo.save(squadLobster);
        lobsterRepo.save(americanLobster);
        lobsterRepo.save(europeanLobster);
        lobsterRepo.save(rockLobster);
        lobsterRepo.save(slipperLobster);
        lobsterRepo.save(furryLobster);
    }
}
