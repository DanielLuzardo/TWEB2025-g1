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
 * Service class responsible for managing the business logic related to Anime Characters.
 *
 * This service acts as a central hub for character data, aggregating information regarding
 * their personal profiles, roles in various anime series, nicknames, and associated voice actors.
 *
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
     * Constructs the service with the necessary data repositories.
     * Dependency injection is handled by Spring.
     *
     * @param charactersRepository          Repository for accessing core character data.
     * @param characterAnimeWorksRepository Repository for accessing the anime series a character appears in.
     * @param characterNicknamesRepository  Repository for accessing character aliases.
     * @param personVoiceWorksRepository    Repository for accessing voice actor associations.
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
     * Retrieves the full profile of a specific character based on their unique identifier.
     *
     * @param id The unique identifier (CharacterMalId) of the character.
     * @return The {@link Characters} entity containing full details.
     * @throws RuntimeException if no character is found with the provided ID.
     */
    public Characters getCharacterById(Integer id) {
        return charactersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Character not found: " + id));
    }

    /**
     * Searches for characters that match the provided name.
     *
     * @param name The name (or partial name) to search for.
     * @return A list of {@link Characters} matching the criteria.
     */
    public List<Characters> getCharacterByName(String name) {
        return charactersRepository.findByName(name);
    }

    /**
     * Retrieves only the name of a character based on their ID.
     *
     * Unlike other retrieval methods in this service, this method returns {@code null}
     * instead of throwing an exception if the character is not found.
     *
     *
     * @param id The unique identifier of the character.
     * @return A {@link Map} containing the key "name", or {@code null} if not found.
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
     * Retrieves the list of anime series (works) in which a specific character appears.
     * This links the character to the anime titles in the database.
     *
     * @param characterId The unique identifier of the character.
     * @return A list of {@link CharacterAnimeWorks} entities.
     */
    public List<CharacterAnimeWorks> getCharacterAnimeWorksByCharacterId(Integer characterId) {
        return characterAnimeWorksRepository.findByCharacter_CharacterMalId(characterId);
    }

    /**
     * Retrieves known aliases or nicknames for a specific character.
     *
     * @param characterId The unique identifier of the character.
     * @return A list of {@link CharacterNicknames}.
     */
    public List<CharacterNicknames> getCharacterNicknameByCharacterId(Integer characterId) {
        return characterNicknamesRepository.findByCharacter_CharacterMalId(characterId);
    }

    /**
     * Retrieves the voice actors (Seiyuu) associated with this character.
     * This connects the character entity to the People entity via voice roles.
     *
     * @param characterId The unique identifier of the character.
     * @return A list of {@link PersonVoiceWorks} representing voice acting roles.
     */
    public List<PersonVoiceWorks> getPersonVoiceWorksByCharacterId(Integer characterId) {
        return personVoiceWorksRepository.findByCharacter_CharacterMalId(characterId);
    }

    /**
     * Retrieves a list of characters associated with a specific anime series, tailored for
     * display on the Anime Details page.
     *
     * This method projects the data into a simplified structure containing only essential UI fields.
     *
     *
     * @param detailsId The ID of the anime (Details) to fetch characters for.
     * @return A list of {@link Map} objects, where each map contains: "name", "imageUrl", and "id".
     * @throws RuntimeException if no characters are found for the given anime ID.
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
     * Retrieves a simplified set of details for a character by ID.
     * Useful for lightweight UI components or pop-ups.
     *
     * @param id The unique identifier of the character.
     * @return A {@link Map} containing: "name", "imageUrl", and "nameKanji".
     * @throws RuntimeException if the character is not found.
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
     * Performs a lightweight search for characters by name, returning simplified data structures.
     * This is ideal for search bars or autocomplete features.
     *
     * @param name The name to search for.
     * @return A list of {@link Map} objects containing: "name", "imageUrl", "id", and "nameKanji".
     * @throws RuntimeException if no characters match the search term.
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