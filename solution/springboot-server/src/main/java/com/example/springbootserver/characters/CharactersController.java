package com.example.springbootserver.characters;

import java.util.List;



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
    public Characters getCharacter(@PathVariable Integer id) {
        return charactersService.getCharacterById(id);
    }

 

    /*@PostMapping("/characters")
    public ResponseEntity<Characters> addCharacters(@RequestBody Characters characters){
        Characters savedCharacters = charactersService.saveCharacters(characters);
        return new ResponseEntity<>(savedCharacters, HttpStatus.CREATED);
    } */
}