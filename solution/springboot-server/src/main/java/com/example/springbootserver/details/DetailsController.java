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
    public ResponseEntity<Details> getDetailsById(
            @Parameter(description = "Anime ID in details table", example = "333")
            @PathVariable Integer id){
        Details details = detailsService.getDetailsById(id);

        if (details == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(details);
    }


    @GetMapping("/title/{title}")
    @Operation(summary = "Search anime by title", description = "Searches for anime by title and returns the details ingo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Details Search completed successfully"),
            @ApiResponse(responseCode = "404", description = "No anime found with that title")
    })
    public ResponseEntity<List<Details>> getDetailsByTitle(
            @Parameter(description = "Anime title to search", example = "Naruto")
            @PathVariable String title){
        List<Details> detailsList = detailsService.getDetailsByTitle(title);

        if (detailsList == null || detailsList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(detailsList);
    }


    @GetMapping("/{id}/summary")
    @Operation(summary = "Get anime summary", description = "Returns basic anime information (title, image URL and id)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Details found successfully"),
            @ApiResponse(responseCode = "404", description = "Anime not found")
    })
    public ResponseEntity<Map<String, Object>> getBasicDetailsById(
            @Parameter(description = "Anime ID in details table", example = "444")
            @PathVariable Integer id){
        Map<String, Object> summary = detailsService.getBasicDetailsById(id);

        if (summary == null || summary.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(summary);
    }

    @Operation(summary = "Get anime title", description = "Returns only the title of an anime by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Title found successfully"),
            @ApiResponse(responseCode = "404", description = "Anime not found")
    })
    @GetMapping("/{id}/title")
    public ResponseEntity<Map<String, Object>> getTitleById(
            @Parameter(description = "Anime ID in details table", example = "555")
            @PathVariable Integer id){
        Map<String, Object> title = detailsService.getTitleById(id);

        if (title == null || title.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(title);
    }


    @GetMapping("/{id}/characters")
    @Operation(summary = "Get anime characters", description = "Returns all characters that appear in a specific anime")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Characters found successfully"),
            @ApiResponse(responseCode = "404", description = "Anime not found")
    })
    public List<Map<String, Object>> getCharactersByAnime(
            @Parameter(description = "Anime ID in details table", example = "555")
            @PathVariable Integer id) {
        return charactersService.getCharactersForAnimeDetails(id);
    }
}