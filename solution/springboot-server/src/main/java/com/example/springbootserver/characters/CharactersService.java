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

/**
 * Service class for managing anime characters operations.
 * Provides methods to retrieve character details, anime roles, nicknames, and associated voice actors.
 *
 * @author TWEB2025-g1
 * @version 1.0
 */
@Service
public class CharactersService {

    private final CharactersRepository charactersRepository;
    private final CharacterAnimeWorksRepository characterAnimeWorksRepository;
    private final CharacterNicknamesRepository characterNicknamesRepository;
    private final PersonVoiceWorksRepository personVoiceWorksRepository;

    /**
     * Constructs a CharactersService with the required repositories.
     *
     * @param charactersRepository repository for character data access
     * @param characterAnimeWorksRepository repository for character anime works data access
     * @param characterNicknamesRepository repository for character nicknames data access
     * @param personVoiceWorksRepository repository for voice works data access
     */
    @Autowired
    public CharactersService(CharactersRepository charactersRepository,
                             CharacterAnimeWorksRepository characterAnimeWorksRepository,
                             CharacterNicknamesRepository characterNicknamesRepository,
                             PersonVoiceWorksRepository personVoiceWorksRepository) {
        this.charactersRepository = charactersRepository;
        this.characterAnimeWorksRepository = characterAnimeWorksRepository;
        this.characterNicknamesRepository = characterNicknamesRepository;
        this.personVoiceWorksRepository = personVoiceWorksRepository;
    }

    /**
     * Retrieves complete character details by their MyAnimeList ID.
     *
     * @param id the MyAnimeList character ID
     * @return the Characters object containing all character information
     * @throws RuntimeException if no character is found with the given ID
     */
    public Characters getCharacterById(Integer id) {
        return charactersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Character not found: " + id));
    }

    /**
     * Searches for characters by their name.
     *
     * @param name the character name to search for
     * @return a list of Characters matching the given name
     */
    public List<Characters> getCharacterByName(String name) {
        return charactersRepository.findByName(name);
    }

    /**
     * Retrieves only the name of a character by its ID.
     * Returns null instead of throwing an exception if not found.
     *
     * @param id the MyAnimeList character ID
     * @return a Map containing the "name" key, or null if not found
     */
    public Map<String, Object> getNameById(Integer id) {
        Characters character = charactersRepository.findById(id).orElse(null);
        if (character == null) {
            return null;
        }
        Map<String, Object> result = new HashMap<>();
        result.put("name", character.getName());
        return result;
    }

    /**
     * Retrieves all anime works where a specific character appears.
     *
     * @param characterId the MyAnimeList character ID
     * @return a list of CharacterAnimeWorks entities
     */
    public List<CharacterAnimeWorks> getCharacterAnimeWorksByCharacterId(Integer characterId) {
        return characterAnimeWorksRepository.findByCharacter_CharacterMalId(characterId);
    }

    /**
     * Retrieves all nicknames or aliases for a specific character.
     *
     * @param characterId the MyAnimeList character ID
     * @return a list of CharacterNicknames objects
     */
    public List<CharacterNicknames> getCharacterNicknameByCharacterId(Integer characterId) {
        return characterNicknamesRepository.findByCharacter_CharacterMalId(characterId);
    }

    /**
     * Retrieves all voice actors (seiyuu) associated with a specific character.
     *
     * @param characterId the MyAnimeList character ID
     * @return a list of PersonVoiceWorks entities
     */
    public List<PersonVoiceWorks> getPersonVoiceWorksByCharacterId(Integer characterId) {
        return personVoiceWorksRepository.findByCharacter_CharacterMalId(characterId);
    }

    /**
     * Retrieves a list of characters for a specific anime ID.
     * Used for displaying character lists on anime detail pages.
     *
     * @param detailsId the MyAnimeList anime ID (Details ID)
     * @return a list of Maps, each containing "name", "imageUrl", and "id" keys
     * @throws RuntimeException if no characters are found for the given anime ID
     */
    public List<Map<String, Object>> getCharactersForAnimeDetails(Integer detailsId) {

        List<Characters> characters = charactersRepository.findAllByAnimeMalId(detailsId);

        if (characters.isEmpty()) {
            throw new RuntimeException("Characters not found for anime with id: " + detailsId);
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

    /**
     * Retrieves basic character information (name, image URL, and kanji name).
     * Used for displaying summary cards without loading full character data.
     *
     * @param id the MyAnimeList character ID
     * @return a Map containing "name", "imageUrl", and "nameKanji" keys
     * @throws RuntimeException if no character is found with the given ID
     */
    public Map<String, Object> getBasicDetailsById(Integer id) {
        Characters characters = charactersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Details not found!!"));

        Map<String, Object> basicInfo = new HashMap<>();
        basicInfo.put("name", characters.getName());
        basicInfo.put("imageUrl", characters.getImage());
        basicInfo.put("nameKanji", characters.getNameKanji());

        return basicInfo;
    }

    /**
     * Searches for basic character information by name.
     * Used for lightweight search results or autocomplete.
     *
     * @param name the character name to search for
     * @return a list of Maps containing "name", "imageUrl", "id", and "nameKanji" keys
     * @throws RuntimeException if no characters are found matching the name
     */
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
            basicInfo.put("nameKanji", character.getNameKanji());

            result.add(basicInfo);
        }

        return result;
    }
}