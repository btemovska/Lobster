package com.example.lobster.controllers;

import com.example.lobster.model.HashTag;
import com.example.lobster.model.Lobster;
import com.example.lobster.repositories.HashTagRepository;
import com.example.lobster.repositories.LobsterRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Optional;

@Controller
public class LobsterController {

    @Resource
    private LobsterRepository lobsterRepo;
    @Resource
    private HashTagRepository hashTagRepo;

    @RequestMapping("/lobsters")
    public String displayLobsters(Model model){
        model.addAttribute("lobsters", lobsterRepo.findAll());
        return "lobstersView";
    }

    @RequestMapping("/lobsters/{id}")
    public String displaySingleLobster(@PathVariable long id, Model model){
        Optional<Lobster> retrievedLobster = lobsterRepo.findById(id);
        Lobster foundLobster = retrievedLobster.get();
        model.addAttribute("lobste", foundLobster);
        return "lobsterView";
    }

    @PostMapping("/lobsters/{id}/add-hashtag")
    public String addHashTagToLobster(@RequestParam String hashTagName, @PathVariable long id){
        HashTag hashTagToAddToLobster;
        Optional<HashTag> hashTagToAddOpt = hashTagRepo.findByName(hashTagName);
        if(hashTagToAddOpt.isEmpty()){
            hashTagToAddToLobster = new HashTag(hashTagName);
            hashTagRepo.save(hashTagToAddToLobster);
        }else{
            hashTagToAddToLobster = hashTagToAddOpt.get();
        }
        Optional<Lobster> retrievedLobster = lobsterRepo.findById(id);
        Lobster foundLobster = retrievedLobster.get();
        foundLobster.addHashTag(hashTagToAddToLobster);
        lobsterRepo.save(foundLobster);
        return "redirect:/lobsters/" + id;
    }

    @PostMapping("/lobsters{id}/delete-hashtag/{hashTagId}")
    public String deleteHashTag(@PathVariable Long id, @PathVariable Long hashTagId){
        Optional<HashTag> hashTagToRemoveOpt = hashTagRepo.findById(hashTagId);
        HashTag hashTagToRemove = hashTagToRemoveOpt.get();
        for(Lobster lobster: hashTagToRemove.getLobsters()){
            lobster.deleteHashTag(hashTagToRemove);
            lobsterRepo.save(lobster);
        }
        Optional<Lobster> lobsterToRemoveHashTagFromOpt = lobsterRepo.findById(id);
        Lobster lobsterToRemoveHashTagFrom = lobsterToRemoveHashTagFromOpt.get();
        lobsterToRemoveHashTagFrom.deleteHashTag(hashTagToRemove);
        lobsterRepo.save(lobsterToRemoveHashTagFrom);
        hashTagRepo.delete(hashTagToRemove);
        return "redirect:/lobsters/" + id;
    }
}
