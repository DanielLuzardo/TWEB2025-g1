package com.example.springbootserver.personDetails;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonDetailsController {
    private final PersonDetailsService personDetailsService;

    @Autowired
    public PersonDetailsController(PersonDetailsService personDetailsService){
        this.personDetailsService = personDetailsService;
    }
}
