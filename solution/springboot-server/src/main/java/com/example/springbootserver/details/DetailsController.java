package com.example.springbootserver.details;

import java.util.List;
import java.util.Map;


import com.example.springbootserver.characters.Characters;
import com.example.springbootserver.characters.CharactersService;
import com.example.springbootserver.details.DetailsService;
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
@RequestMapping("/details")
@Tag(name = "Anime Details", description = "Endpoints for retrieving anime information")
public class DetailsController {
    private final DetailsService detailsService;
    private final CharactersService charactersService;

    @Autowired
    public DetailsController(DetailsService detailsService, CharactersService charactersService) {

        this.detailsService = detailsService;
        this.charactersService = charactersService;

    }

    @GetMapping("/{id}")
    @Operation(summary = "Get anime by ID", description = "Retrieves complete anime details by its MyAnimeList ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Anime found successfully"),
            @ApiResponse(responseCode = "404", description = "Anime not found")
    })
    public Details getDetailsById(@PathVariable Integer id){
        return detailsService.getDetailsById(id);
    }

    @GetMapping
    public List<Details> getDetailsByTitle(@RequestParam String title){
        return detailsService.getDetailsByTitle(title);
    }


    @GetMapping("/{id}/summary")
    public Map<String, Object> getBasicDetailsById(@PathVariable Integer id){
        return detailsService.getBasicDetailsById(id);
    }

    @GetMapping("/{id}/title")
    public Map<String, Object> getTitleById(@PathVariable Integer id){
        return detailsService.getTitleById(id);
    }
    @GetMapping("/{id}/characters")
    public List<Map<String, Object>> getCharactersByAnime(@PathVariable Integer id) {
        return charactersService.getCharactersForAnimeDetails(id);
    }
}