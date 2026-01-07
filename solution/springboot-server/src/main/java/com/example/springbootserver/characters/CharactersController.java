package com.example.springbootserver.characters;

import java.util.List;
import java.util.Map;


import com.example.springbootserver.characterAnimeWorks.CharacterAnimeWorks;
import com.example.springbootserver.characterNicknames.CharacterNicknames;
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

@RestController
@RequestMapping("/characters")
@Tag(name = "Characters", description = "Endpoints for searching character information")
public class CharactersController {
    private final CharactersService charactersService;

    @Autowired
    public CharactersController(CharactersService charactersService){
        this.charactersService = charactersService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get character by ID", description = "Retrieves complete character details by its MyAnimeList ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Character found successfully"),
            @ApiResponse(responseCode = "404", description = "Character not found")
    })
    public Characters getCharacterById(
            @Parameter(description = "MyAnimeList character ID", example = "1")
            @PathVariable Integer id) {
        return charactersService.getCharacterById(id);
    }


    @GetMapping("/name/{name}")
    @Operation(summary = "Search characters by name", description = "Searches for character by name and returns its information ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search completed successfully"),
            @ApiResponse(responseCode = "404", description = "No characters found with that name")
    })
    public List<Characters> getCharacterByName(
            @Parameter(description = "Character name to search for", example = "Goku")
            @PathVariable String name){
        return charactersService.getCharacterByName(name);
    }



    @GetMapping("/{id}/anime-works")
    @Operation(summary = "Get character's anime appearances", description = "Returns all anime where this character appears")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Anime works returned successfully"),
            @ApiResponse(responseCode = "404", description = "Character not found")
    })
    public List<CharacterAnimeWorks> getAnimeWorks(
            @Parameter(description = "Character ID", example = "1")
            @PathVariable Integer id) {
        return charactersService.getCharacterAnimeWorksByCharacterId(id);
    }

    @GetMapping("/{id}/voice-actors")
    @Operation(summary = "Get character's voice actors", description = "Return all voice actors who have voiced this character")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Voice actors returned successfully"),
            @ApiResponse(responseCode = "404", description = "Character not found")
    })
    public List<PersonVoiceWorks> getPersonVoiceWorks(
            @Parameter(description = "character ID", example = "1")
            @PathVariable Integer id) {
        return charactersService.getPersonVoiceWorksByCharacterId(id);
    }

    @GetMapping("/{id}/nicknames")
    @Operation(summary = "Get character's nicknames", description = "Returns all nicknames for a specific character")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nicknames returned successfully"),
            @ApiResponse(responseCode = "404", description = "Character not found")
    })
    public List<CharacterNicknames> getNicknames(
            @Parameter(description = "character ID", example = "1")
            @PathVariable Integer id) {
        return charactersService.getCharacterNicknameByCharacterId(id);
    }
    @GetMapping("/{id}/summary")
    @Operation(summary = "Get character summary", description = "Returns basic character information (name, image URL, and kanji name)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Summary returned successfully"),
            @ApiResponse(responseCode = "404", description = "Character not found")
    })
    public Map<String, Object> getBasicDetailsById(
            @Parameter(description = "character ID", example = "1")
            @PathVariable Integer id){
        return charactersService.getBasicDetailsById(id);
    }

    @GetMapping("/by-name/{name}")
    @Operation(summary = "Get character summaries by name", description = "Searches characters by name and returns basic information for each match")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search completed successfully"),
            @ApiResponse(responseCode = "404", description = "No characters found with that name")
    })
    public List<Map<String, Object>> getBasicDetailsByName(
            @Parameter(description = "Character name to search for", example = "Spike")
            @PathVariable String name) {
        return charactersService.getBasicDetailsByName(name);
    }

}