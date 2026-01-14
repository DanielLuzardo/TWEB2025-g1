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
 * Service class for managing person (voice actors and staff) related operations.
 * Provides methods to retrieve person details, alternate names, anime works, and voice acting roles.
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
     * Constructs a PersonDetailsService with the required repositories.
     *
     * @param personDetailsRepository repository for person details data access
     * @param personAlternateNameRepository repository for alternate names data access
     * @param personAnimeWorksRepository repository for anime works data access
     * @param personVoiceWorksRepository repository for voice works data access
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
     * Retrieves complete person details by their MyAnimeList ID.
     *
     * @param id the MyAnimeList person ID
     * @return the PersonDetails object containing all person information
     * @throws RuntimeException if no person is found with the given ID
     */
    public PersonDetails getPersonDetailsById(Integer id) {
        return personDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No person found with id: " + id));
    }

    /**
     * Searches for persons by their name.
     *
     * @param name the name to search for
     * @return a list of PersonDetails matching the given name
     */
    public List<PersonDetails> getPersonDetailsByName(String name) {
        return personDetailsRepository.findByName(name);
    }

    /**
     * Retrieves all alternate names for a specific person.
     *
     * @param personId the MyAnimeList person ID
     * @return a list of PersonAlternateName objects for the given person
     */
    public List<PersonAlternateName> getPersonAlternateNameByPersonId(Integer personId) {
        return personAlternateNameRepository.findByPerson_PersonMalId(personId);
    }

    /**
     * Retrieves all anime works (staff roles) for a specific person.
     *
     * @param personId the MyAnimeList person ID
     * @return a list of PersonAnimeWorks representing the person's staff contributions
     */
    public List<PersonAnimeWorks> getPersonAnimeWorksByPersonId(Integer personId) {
        return personAnimeWorksRepository.findByPerson_PersonMalId(personId);
    }

    /**
     * Retrieves all voice acting roles for a specific person.
     *
     * @param personId the MyAnimeList person ID
     * @return a list of PersonVoiceWorks representing characters voiced by this person
     */
    public List<PersonVoiceWorks> getPersonVoiceWorksByPersonId(Integer personId) {
        return personVoiceWorksRepository.findByPerson_PersonMalId(personId);
    }

    /**
     * Retrieves basic person information (name, image URL, and ID).
     * Used for displaying summary cards without loading full person data.
     *
     * @param id the MyAnimeList person ID
     * @return a Map containing "name", "imageUrl", and "personMalId" keys
     * @throws RuntimeException if no person is found with the given ID
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