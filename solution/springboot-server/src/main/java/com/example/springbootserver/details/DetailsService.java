package com.example.springbootserver.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service class for managing anime details operations.
 * Provides methods to retrieve anime information by ID or title.
 *
 * @author TWEB2025-g1
 * @version 1.0
 */
@Service
public class DetailsService {
    private final DetailsRepository detailsRepository;

    /**
     * Constructs a DetailsService with the required repository.
     *
     * @param detailsRepository repository for anime details data access
     */
    @Autowired
    public DetailsService(DetailsRepository detailsRepository) {
        this.detailsRepository = detailsRepository;
    }

    /**
     * Retrieves complete anime details by its MyAnimeList ID.
     *
     * @param id the MyAnimeList anime ID
     * @return the Details object containing all anime information
     * @throws RuntimeException if no anime is found with the given ID
     */
    public Details getDetailsById(Integer id) {
        return detailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Details not found!!"));
    }

    /**
     * Retrieves basic anime information (title, image URL, and ID).
     * Used for displaying summary cards without loading full anime data.
     *
     * @param id the MyAnimeList anime ID
     * @return a Map containing "title", "imageUrl", and "animeMalId" keys
     * @throws RuntimeException if no anime is found with the given ID
     */
    public Map<String, Object> getBasicDetailsById(Integer id) {
        Details details = detailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Details not found!!"));

        Map<String, Object> basicInfo = new HashMap<>();
        basicInfo.put("title", details.getTitle());
        basicInfo.put("imageUrl", details.getImageUrl());
        basicInfo.put("animeMalId", details.getMalId());

        return basicInfo;
    }

    /**
     * Retrieves only the title of an anime by its ID.
     *
     * @param id the MyAnimeList anime ID
     * @return a Map containing only the "title" key
     * @throws RuntimeException if no anime is found with the given ID
     */
    public Map<String, Object> getTitleById(Integer id) {
        Details details = detailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Details not found"));

        Map<String, Object> result = new HashMap<>();
        result.put("title", details.getTitle());
        return result;
    }

    /**
     * Searches for anime by their title.
     *
     * @param title the anime title to search for
     * @return a list of Details matching the given title
     */
    public List<Details> getDetailsByTitle(String title) {
        return detailsRepository.findByTitle(title);
    }
}