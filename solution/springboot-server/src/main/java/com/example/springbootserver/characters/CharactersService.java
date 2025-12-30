package com.example.springbootserver.characters;


import com.example.springbootserver.characterAnimeWorks.CharacterAnimeWorks;
import com.example.springbootserver.characterNicknames.CharacterNicknames;
import com.example.springbootserver.details.Details;
import com.example.springbootserver.personVoiceWorks.PersonVoiceWorks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springbootserver.characterAnimeWorks.CharacterAnimeWorksRepository;
import com.example.springbootserver.characterNicknames.CharacterNicknamesRepository;
import com.example.springbootserver.personVoiceWorks.PersonVoiceWorksRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //Modified to return also the image
    /*
    public Map<String, Object> getNameById(Integer id) {
        Characters character = charactersRepository.findById(id).orElse(null);
        if (character == null) {
            return null;
        }
        Map<String, Object> result = new HashMap<>();
        result.put("name", character.getName());
        result.put("imageUrl", character.getImage());
        result.put("nameKanji",  character.getNameKanji());
        return result;
    }

    */

    public List<CharacterAnimeWorks> getCharacterAnimeWorksByCharacterId(Integer characterId) {
        return characterAnimeWorksRepository.findByCharacter_CharacterMalId(characterId);
    }
    public List<CharacterNicknames> getCharacterNicknameByCharacterId(Integer characterId) {
        return characterNicknamesRepository.findByCharacter_CharacterMalId(characterId);
    }
    public List<PersonVoiceWorks> getPersonVoiceWorksByCharacterId(Integer characterId) {
        return personVoiceWorksRepository.findByCharacter_CharacterMalId(characterId);
    }

    public List<Map<String, Object>> getCharactersForAnimeDetails(Integer detailsId) {

        List<Characters> characters = charactersRepository.findAllByAnimeMalId(detailsId);

        if (characters.isEmpty()) {
            throw new RuntimeException("Details not found for id: " + detailsId);
        }

        List<Map<String, Object>> result = new ArrayList<>();

        for (Characters character : characters) {
            Map<String, Object> basicInfo = new HashMap<>();
            basicInfo.put("name", character.getName());
            basicInfo.put("imageUrl", character.getImage());
            basicInfo.put("id", character.getCharacterMalId());
            result.add(basicInfo);
        }

        return result;
    }

    public Map<String, Object> getBasicDetailsById(Integer id){
        Characters characters = charactersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Details not found!!"));

        Map<String, Object> basicInfo = new HashMap<>();
        basicInfo.put("name", characters.getName());
        basicInfo.put("imageUrl", characters.getImage());
        basicInfo.put("nameKanji",  characters.getNameKanji());


        return basicInfo;
    }
    public List<Map<String, Object>> getBasicDetailsByName(String name) {

        List<Characters> characters = charactersRepository.findByName(name);
        if (characters.isEmpty()) {
            throw new RuntimeException("Details not found for id: " + name);
        }

        List<Map<String, Object>> result = new ArrayList<>();

        for (Characters character : characters) {
            Map<String, Object> basicInfo = new HashMap<>();
            basicInfo.put("name", character.getName());
            basicInfo.put("imageUrl", character.getImage());
            basicInfo.put("id", character.getCharacterMalId());
            basicInfo.put("nameKanji",  character.getNameKanji());

            result.add(basicInfo);
        }

        return result;
    }



}