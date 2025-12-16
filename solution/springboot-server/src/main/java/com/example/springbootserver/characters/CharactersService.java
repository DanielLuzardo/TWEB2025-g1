package com.example.springbootserver.characters;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharactersService {
    private final CharactersRepository charactersRepository;

    @Autowired
    public CharactersService(CharactersRepository charactersRepository){
        this.charactersRepository = charactersRepository;
    }
    public Characters getCharacterById(Integer id) {
        return charactersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Character not found: " + id));
    }



}