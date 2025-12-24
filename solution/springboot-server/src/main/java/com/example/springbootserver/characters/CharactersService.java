package com.example.springbootserver.characters;


import com.example.springbootserver.characterAnimeWorks.CharacterAnimeWorks;
import com.example.springbootserver.characterNicknames.CharacterNicknames;
import com.example.springbootserver.personVoiceWorks.PersonVoiceWorks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springbootserver.characterAnimeWorks.CharacterAnimeWorksRepository;
import com.example.springbootserver.characterNicknames.CharacterNicknamesRepository;
import com.example.springbootserver.personVoiceWorks.PersonVoiceWorksRepository;

import java.util.List;

@Service
public class CharactersService {
    private final CharactersRepository charactersRepository;
    private final CharacterAnimeWorksRepository characterAnimeWorksRepository;
    private final CharacterNicknamesRepository characterNicknamesRepository;
    private final PersonVoiceWorksRepository personVoiceWorksRepository;

    @Autowired
    public CharactersService(CharactersRepository charactersRepository, CharacterAnimeWorksRepository characterAnimeWorksRepository, CharacterNicknamesRepository characterNicknamesRepository,PersonVoiceWorksRepository personVoiceWorksRepository) {
        this.charactersRepository = charactersRepository;
        this.characterAnimeWorksRepository = characterAnimeWorksRepository;
        this.characterNicknamesRepository = characterNicknamesRepository;
        this.personVoiceWorksRepository = personVoiceWorksRepository;
    }
    public Characters getCharacterById(Integer id) {
        return charactersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Character not found: " + id));
    }

    public List<Characters> getCharacterByName(String name) {
        return charactersRepository.findByName(name);
    }

    public List<CharacterAnimeWorks> getCharacterAnimeWorksByCharacterId(Integer characterId) {
        return characterAnimeWorksRepository.findByCharacter_CharacterMalId(characterId);
    }
    public List<CharacterNicknames> getCharacterNicknameByCharacterId(Integer characterId) {
        return characterNicknamesRepository.findByCharacter_CharacterMalId(characterId);
    }
    public List<PersonVoiceWorks> getPersonVoiceWorksByCharacterId(Integer characterId) {
        return personVoiceWorksRepository.findByCharacter_CharacterMalId(characterId);
    }


}