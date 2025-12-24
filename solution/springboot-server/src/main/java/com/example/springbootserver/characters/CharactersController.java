package com.example.springbootserver.characters;

import java.util.List;


import com.example.springbootserver.characterAnimeWorks.CharacterAnimeWorks;
import com.example.springbootserver.characterNicknames.CharacterNicknames;
import com.example.springbootserver.personVoiceWorks.PersonVoiceWorks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/characters")

public class CharactersController {
    private final CharactersService charactersService;

    @Autowired
    public CharactersController(CharactersService charactersService){
        this.charactersService = charactersService;
    }
    @GetMapping("/{id}")
    public Characters getCharacterById(@PathVariable Integer id) {
        return charactersService.getCharacterById(id);
    }

    @GetMapping
    public List<Characters> getCharacterByName(@RequestParam String name){
        return charactersService.getCharacterByName(name);
    }


    @GetMapping("/{id}/anime-works")
    public List<CharacterAnimeWorks> getAnimeWorks(@PathVariable Integer id) {
        return charactersService.getCharacterAnimeWorksByCharacterId(id);
    }

    @GetMapping("/{id}/voice-actors")
    public List<PersonVoiceWorks> getPersonVoiceWorks(@PathVariable Integer id) {
        return charactersService.getPersonVoiceWorksByCharacterId(id);
    }

    @GetMapping("/{id}/nicknames")
    public List<CharacterNicknames> getNicknames(@PathVariable Integer id) {
        return charactersService.getCharacterNicknameByCharacterId(id);
    }


}