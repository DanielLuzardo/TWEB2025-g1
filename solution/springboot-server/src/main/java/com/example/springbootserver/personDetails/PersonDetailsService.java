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


@Service
public class PersonDetailsService {
    private final PersonDetailsRepository personDetailsRepository;
    private final PersonAlternateNameRepository personAlternateNameRepository;
    private final PersonAnimeWorksRepository personAnimeWorksRepository;
    private final PersonVoiceWorksRepository personVoiceWorksRepository;

    @Autowired
    public PersonDetailsService(PersonDetailsRepository personDetailsRepository, PersonAlternateNameRepository personAlternateNameRepository, PersonAnimeWorksRepository personAnimeWorksRepository, PersonVoiceWorksRepository personVoiceWorksRepository) {
        this.personDetailsRepository = personDetailsRepository;
        this.personAlternateNameRepository = personAlternateNameRepository;
        this.personAnimeWorksRepository = personAnimeWorksRepository;
        this.personVoiceWorksRepository = personVoiceWorksRepository;
    }

    public PersonDetails getPersonDetailsById(Integer id) {
        return personDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No person found with id: " + id));
    }

    public List<PersonDetails> getPersonDetailsByName(String name) {
        return personDetailsRepository.findByName(name);
    }
    public List<PersonAlternateName> getPersonAlternateNameByPersonId(Integer personId) {
        return personAlternateNameRepository.findByPerson_PersonMalId(personId);
    }

    public List<PersonAnimeWorks> getPersonAnimeWorksByPersonId(Integer personId) {
        return personAnimeWorksRepository.findByPerson_PersonMalId(personId);
    }
    public List<PersonVoiceWorks> getPersonVoiceWorksByPersonId(Integer personId) {
        return personVoiceWorksRepository.findByPerson_PersonMalId(personId);
    }

    public Map<String, Object> getBasicDetailsById(Integer id){
        PersonDetails details = personDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Details not found!!"));

        Map<String, Object> basicInfo = new HashMap<>();
        basicInfo.put("name", details.getName());
        basicInfo.put("imageUrl", details.getImageUrl());
        basicInfo.put("personMalId", details.getPersonMalId());

        return basicInfo;
    }

}
