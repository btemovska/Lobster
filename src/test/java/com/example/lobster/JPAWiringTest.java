package com.example.lobster;

import com.example.lobster.model.HashTag;
import com.example.lobster.model.Lobster;
import com.example.lobster.model.Specie;
import com.example.lobster.repositories.HashTagRepository;
import com.example.lobster.repositories.LobsterRepository;
import com.example.lobster.repositories.SpecieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class JPAWiringTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private SpecieRepository specieRepo;
    @Autowired
    private LobsterRepository lobsterRepo;
    @Autowired
    private HashTagRepository hashTagRepo;

    @Test
    public void specieShouldHaveAListOfLobsters(){
        Specie testSpecie1 = new Specie("Test Type1");
        Specie testSpecie2 = new Specie("Test Type2");
        Lobster testLobster1 = new Lobster("Name", "Description","", testSpecie1);
        Lobster testLobster2 = new Lobster("Name", "Description", "", testSpecie1);
        specieRepo.save(testSpecie1);
        specieRepo.save(testSpecie2);
        lobsterRepo.save(testLobster1);
        lobsterRepo.save(testLobster2);
        entityManager.flush();
        entityManager.clear();

        Optional<Specie> retrievedSpecieOpt = specieRepo.findById(testSpecie1.getId());
        Specie retrievedSpecie = retrievedSpecieOpt.get();
        assertThat(retrievedSpecie.getLobsters()).contains(testLobster1);
    }

    @Test
    public void lobstersShouldHaveHashTags(){
        Specie testSpecie = new Specie("Test Type");
        Lobster testLobster1 = new Lobster("Name", "Description", "", testSpecie);
        HashTag hashTag1 = new HashTag("HashTag1");
        HashTag hashTag2 = new HashTag("HashTag2");
        specieRepo.save(testSpecie);
        lobsterRepo.save(testLobster1);
        hashTagRepo.save(hashTag1);
        hashTagRepo.save(hashTag2);
        testLobster1.addHashTag(hashTag1);
        testLobster1.addHashTag(hashTag2);
//        lobsterRepo.save(testLobster1);

        entityManager.flush();
        entityManager.clear();

        Lobster retrievedLobster = lobsterRepo.findById(testLobster1.getId()).get();
        assertThat(retrievedLobster.getHashTags()).contains(hashTag1, hashTag2);
    }
}
