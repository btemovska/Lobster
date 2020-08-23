package com.example.lobster.rest.controllers;

import com.example.lobster.model.HashTag;
import com.example.lobster.model.Lobster;
import com.example.lobster.repositories.HashTagRepository;
import com.example.lobster.repositories.LobsterRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Optional;

@RestController
@CrossOrigin
public class LobsterRestController {

    @Resource
    private LobsterRepository lobsterRepo;

    @Resource
    private HashTagRepository hashTagRepo;

    @GetMapping("/api/lobsters")
    public Collection<Lobster> getLobster() {
        return (Collection<Lobster>) lobsterRepo.findAll();
    }

    @GetMapping("/api/lobsters/{id}")
    public Optional<Lobster> getLobster(@PathVariable Long id) {
        return lobsterRepo.findById(id);
    }

    @PostMapping("/api/lobsters/{id}/add-hashtag")
    public Optional<Lobster> addHashTagToLobster(@RequestBody String body, @PathVariable Long id) throws JSONException {
        JSONObject newHashTag = new JSONObject(body);
        String hashTagName = newHashTag.getString("hashTagName");
        Optional<HashTag> hashTagToAddOpt = hashTagRepo.findByName(hashTagName);
        if (hashTagToAddOpt.isPresent()) {
            Optional<Lobster> lobsterToAddHashTagToOpt = lobsterRepo.findById(id);
            Lobster lobsterToAddHashTagTo = lobsterToAddHashTagToOpt.get();
            lobsterToAddHashTagTo.addHashTag(hashTagToAddOpt.get());
            lobsterRepo.save(lobsterToAddHashTagTo);
        }
        return lobsterRepo.findById(id);
    }

}