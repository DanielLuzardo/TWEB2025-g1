package com.example.springbootserver.personDetails;


import com.example.springbootserver.characters.Characters;
import com.example.springbootserver.personAlternateName.PersonAlternateName;
import com.example.springbootserver.personAnimeWorks.PersonAnimeWorks;
import com.example.springbootserver.personVoiceWorks.PersonVoiceWorks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/personDetails")
@Tag(name = "Person Details", description = "Endpoints for searching voice actors and staff information")
public class PersonDetailsController {
    private final PersonDetailsService personDetailsService;

    @Autowired
    public PersonDetailsController(PersonDetailsService personDetailsService){
        this.personDetailsService = personDetailsService;
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get person by ID", description = "Return complete person details (voice actor/staff) by their BDD ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person found successfully"),
            @ApiResponse(responseCode = "404", description = "Person not found")
    })
    public ResponseEntity<PersonDetails> getPersonDetails(
            @Parameter(description = "BDD person ID", example = "1")
            @PathVariable Integer id){
        PersonDetails person = personDetailsService.getPersonDetailsById(id);

        if (person == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(person);
    }


    @GetMapping("/name/{name}")
    @Operation(summary = "Search person by name", description = "Searches for voice actors/staff by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search completed successfully"),
            @ApiResponse(responseCode = "404", description = "No person found with that name")
    })
    public ResponseEntity<List<PersonDetails>> getPersonDetailsByName(
            @Parameter(description = "Person name to search for", example = "Miyazaki")
            @PathVariable String name){
        List<PersonDetails> persons = personDetailsService.getPersonDetailsByName(name);

        if (persons == null || persons.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(persons);
    }

    @GetMapping("/{id}/alternate-name")
    @Operation(summary = "Get person's alternate names", description = "Returns all alternate names for a specific person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alternate names returned successfully"),
            @ApiResponse(responseCode = "404", description = "Person not found")
    })
    public List<PersonAlternateName> getAlternateName(
            @Parameter(description = "BDD person ID", example = "123")
            @PathVariable Integer id) {
        return personDetailsService.getPersonAlternateNameByPersonId(id);
    }

    @GetMapping("/{id}/anime-works")
    @Operation(summary = "Get person's anime works", description = "Returned all anime where this person has worked")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Anime works returned successfully"),
            @ApiResponse(responseCode = "404", description = "Person not found")
    })
    public List<PersonAnimeWorks> getAnimeWorks(
            @Parameter(description = "BDD person ID", example = "1")
            @PathVariable Integer id) {
        return personDetailsService.getPersonAnimeWorksByPersonId(id);
    }
    @GetMapping("/{id}/voice-works")
    @Operation(summary = "Get person's voice acting roles", description = "Returns all characters this person has voiced")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Voice works returned successfully"),
            @ApiResponse(responseCode = "404", description = "Person not found")
    })
    public List<PersonVoiceWorks> getVoiceWorks(
            @Parameter(description = "BDD person ID", example = "1")
            @PathVariable Integer id) {
        return personDetailsService.getPersonVoiceWorksByPersonId(id);
    }
    @GetMapping("/{id}/summary")
    @Operation(summary = "Get person summary", description = "Retrieves basic person information (name and image URL)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Summary returned successfully"),
            @ApiResponse(responseCode = "404", description = "Person not found")
    })
    public ResponseEntity<Map<String, Object>> getBasicDetailsById(
            @Parameter(description = "BDD person ID", example = "1")
            @PathVariable Integer id){
        Map<String, Object> summary =
                personDetailsService.getBasicDetailsById(id);

        if (summary == null || summary.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(summary);
    }
}
