package com.example.springbootserver.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service class responsible for managing the business logic related to Anime Details.
 *
 * This service handles the retrieval of anime metadata, allowing searches by ID or title,
 * and providing both full detailed views and simplified data projections for UI components.
 *
 *
 * @author TWEB2025-g1
 * @version 1.0
 */
@Service
public class DetailsService {

    private final DetailsRepository detailsRepository;

    /**
     * Constructs the service with the necessary repository.
     * Dependency injection is managed by Spring.
     *
     * @param detailsRepository The repository for accessing {@link Details} data in the database.
     */
    @Autowired
    public DetailsService(DetailsRepository detailsRepository) {
        this.detailsRepository = detailsRepository;
    }

    /**
     * Retrieves the complete detailed information for a specific anime series based on its ID.
     *
     * @param id The unique identifier of the anime.
     * @return The {@link Details} entity containing all available metadata for the anime.
     * @throws RuntimeException if no anime is found with the provided ID.
     */
    public Details getDetailsById(Integer id) {
        return detailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Details not found!!"));
    }

    /**
     * Retrieves a simplified subset of details for a specific anime.
     *
     * This method is designed for lightweight UI displays (e.g., thumbnail grids or search results)
     * where full metadata is not required, reducing data transfer overhead.
     *
     *
     * @param id The unique identifier of the anime.
     * @return A {@link Map} containing the keys: "title", "imageUrl", and "animeMalId".
     * @throws RuntimeException if the anime details are not found.
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
     * Retrieves only the title of a specific anime based on its ID.
     * Useful for dropdowns, breadcrumbs, or headers where only the name is needed.
     *
     * @param id The unique identifier of the anime.
     * @return A {@link Map} containing a single key "title" with the anime's name.
     * @throws RuntimeException if the anime details are not found.
     */
    public Map<String, Object> getTitleById(Integer id) {
        Details details = detailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Details not found"));

        Map<String, Object> result = new HashMap<>();
        result.put("title", details.getTitle());
        return result;
    }

    /**
     * Searches for anime series that match a specific title.
     * This method typically supports partial matches depending on the repository implementation.
     *
     * @param title The title (or part of the title) to search for.
     * @return A {@link List} of {@link Details} entities matching the search criteria.
     */
    public List<Details> getDetailsByTitle(String title) {
        return detailsRepository.findByTitle(title);
    }
}