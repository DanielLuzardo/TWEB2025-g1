package com.example.springbootserver.personDetails;


import com.example.springbootserver.characters.Characters;
import com.example.springbootserver.personAlternateName.PersonAlternateName;
import com.example.springbootserver.personAnimeWorks.PersonAnimeWorks;
import com.example.springbootserver.personVoiceWorks.PersonVoiceWorks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/personDetails")

public class PersonDetailsController {
    private final PersonDetailsService personDetailsService;

    @Autowired
    public PersonDetailsController(PersonDetailsService personDetailsService){
        this.personDetailsService = personDetailsService;
    }

    @GetMapping("/{id}")
    public PersonDetails getPersonDetails(@PathVariable Integer id){
        return personDetailsService.getPersonDetailsById(id);
    }
    @GetMapping
    public List<PersonDetails> getPersonDetailsByName(@RequestParam String name){
        return personDetailsService.getPersonDetailsByName(name);
    }
    @GetMapping("/{id}/alternate-name")
    public List<PersonAlternateName> getAlternateName(@PathVariable Integer id) {
        return personDetailsService.getPersonAlternateNameByPersonId(id);
    }
    @GetMapping("/{id}/anime-works")
    public List<PersonAnimeWorks> getAnimeWorks(@PathVariable Integer id) {
        return personDetailsService.getPersonAnimeWorksByPersonId(id);
    }
    @GetMapping("/{id}/voice-works")
    public List<PersonVoiceWorks> getVoiceWorks(@PathVariable Integer id) {
        return personDetailsService.getPersonVoiceWorksByPersonId(id);
    }
    @GetMapping("/{id}/summary")
    public Map<String, Object> getBasicDetailsById(@PathVariable Integer id){
        return personDetailsService.getBasicDetailsById(id);
    }
}
