package com.example.springbootserver.personDetails;

import com.example.springbootserver.characters.Characters;
import com.example.springbootserver.characters.CharactersRepository;
import com.example.springbootserver.details.Details;
import com.example.springbootserver.personAnimeWorks.PersonAnimeWorksRepository;
import com.example.springbootserver.personVoiceWorks.PersonVoiceWorks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springbootserver.personAlternateName.PersonAlternateNameRepository;
import com.example.springbootserver.personAlternateName.PersonAlternateName;
import com.example.springbootserver.personAnimeWorks.PersonAnimeWorks;
import com.example.springbootserver.personVoiceWorks.PersonVoiceWorksRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service class responsible for the business logic related to People (Staff and Voice Actors).
 *
 * This service acts as an orchestrator that aggregates data from multiple repositories,
 * providing access to personal details, alternate names, anime production roles, and voice acting roles.
 *
 *
 * @author TWEB2025-g1
 * @version 1.0
 */
@Service
public class PersonDetailsService {

    private final PersonDetailsRepository personDetailsRepository;
    private final PersonAlternateNameRepository personAlternateNameRepository;
    private final PersonAnimeWorksRepository personAnimeWorksRepository;
    private final PersonVoiceWorksRepository personVoiceWorksRepository;

    /**
     * Constructs the service with the necessary data repositories.
     * Dependency injection is handled by Spring's IoC container.
     *
     * @param personDetailsRepository       Repository for accessing basic person biographical data.
     * @param personAlternateNameRepository Repository for accessing alternate names or aliases.
     * @param personAnimeWorksRepository    Repository for accessing anime production staff roles.
     * @param personVoiceWorksRepository    Repository for accessing voice acting roles.
     */
    @Autowired
    public PersonDetailsService(PersonDetailsRepository personDetailsRepository,
                                PersonAlternateNameRepository personAlternateNameRepository,
                                PersonAnimeWorksRepository personAnimeWorksRepository,
                                PersonVoiceWorksRepository personVoiceWorksRepository) {
        this.personDetailsRepository = personDetailsRepository;
        this.personAlternateNameRepository = personAlternateNameRepository;
        this.personAnimeWorksRepository = personAnimeWorksRepository;
        this.personVoiceWorksRepository = personVoiceWorksRepository;
    }

    /**
     * Retrieves detailed information about a specific person based on their unique identifier.
     *
     * @param id The unique identifier (typically PersonMalId) of the person.
     * @return The {@link PersonDetails} entity containing biographical information.
     * @throws RuntimeException if no person is found with the provided ID.
     */
    public PersonDetails getPersonDetailsById(Integer id) {
        return personDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No person found with id: " + id));
    }

    /**
     * Searches for people whose names match the provided string.
     *
     * @param name The name of the person to search for.
     * @return A list of {@link PersonDetails} matching the given name. Returns an empty list if no matches are found.
     */
    public List<PersonDetails> getPersonDetailsByName(String name) {
        return personDetailsRepository.findByName(name);
    }

    /**
     * Retrieves a list of alternate names or aliases for a specific person.
     * This may include nicknames, names in native script (e.g., Kanji), or stage names.
     *
     * @param personId The unique identifier of the person.
     * @return A list of {@link PersonAlternateName} entities associated with the person.
     */
    public List<PersonAlternateName> getPersonAlternateNameByPersonId(Integer personId) {
        return personAlternateNameRepository.findByPerson_PersonMalId(personId);
    }

    /**
     * Retrieves the history of anime production works (Staff roles) for a specific person.
     * This includes roles such as director, scriptwriter, key animator, etc.
     *
     * @param personId The unique identifier of the person.
     * @return A list of {@link PersonAnimeWorks} entities detailing the staff roles.
     */
    public List<PersonAnimeWorks> getPersonAnimeWorksByPersonId(Integer personId) {
        return personAnimeWorksRepository.findByPerson_PersonMalId(personId);
    }

    /**
     * Retrieves the history of voice acting roles for a specific person.
     * This links the person (Voice Actor/Seiyuu) to the characters they have voiced.
     *
     * @param personId The unique identifier of the person.
     * @return A list of {@link PersonVoiceWorks} entities detailing the voice acting roles.
     */
    public List<PersonVoiceWorks> getPersonVoiceWorksByPersonId(Integer personId) {
        return personVoiceWorksRepository.findByPerson_PersonMalId(personId);
    }

    /**
     * Retrieves a simplified subset of details for a specific person.
     *
     * This method is useful for lightweight UI components (like list items or cards)
     * that do not require the full biographical data.
     *
     *
     * @param id The unique identifier of the person.
     * @return A {@link Map} containing specific fields: "name", "imageUrl", and "personMalId".
     * @throws RuntimeException if the person details are not found.
     */
    public Map<String, Object> getBasicDetailsById(Integer id) {
        PersonDetails details = personDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Details not found!!"));

        Map<String, Object> basicInfo = new HashMap<>();
        basicInfo.put("name", details.getName());
        basicInfo.put("imageUrl", details.getImageUrl());
        basicInfo.put("personMalId", details.getPersonMalId());

        return basicInfo;
    }
}