package com.example.springbootserver.personDetails;


import com.example.springbootserver.characters.CharactersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonDetailsService {
    private final PersonDetailsRepository personDetailsRepository;

    @Autowired
    public PersonDetailsService(PersonDetailsRepository personDetailsRepository) {
        this.personDetailsRepository = personDetailsRepository;
    }
}
