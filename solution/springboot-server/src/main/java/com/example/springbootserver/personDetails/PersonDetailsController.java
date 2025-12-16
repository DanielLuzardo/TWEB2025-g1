package com.example.springbootserver.personDetails;


import com.example.springbootserver.characters.Characters;
import com.example.springbootserver.personAlternateName.PersonAlternateName;
import com.example.springbootserver.personAnimeWorks.PersonAnimeWorks;
import com.example.springbootserver.personVoiceWorks.PersonVoiceWorks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
