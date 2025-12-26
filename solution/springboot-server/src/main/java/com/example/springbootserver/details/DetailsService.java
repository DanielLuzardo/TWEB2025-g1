package com.example.springbootserver.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DetailsService {
    private final DetailsRepository detailsRepository;

    @Autowired
    public DetailsService(DetailsRepository detailsRepository) {
        this.detailsRepository = detailsRepository;
    }

    public Details getDetailsById(Integer id){
        return detailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Details not found!!"));
    }

    public Map<String, Object> getBasicDetailsById(Integer id){
        Details details = detailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Details not found!!"));

        Map<String, Object> basicInfo = new HashMap<>();
        basicInfo.put("title", details.getTitle());
        basicInfo.put("imageUrl", details.getImageUrl());
        basicInfo.put("animeMalId", details.getMalId());

        return basicInfo;
    }

    public Map<String, Object> getTitleById(Integer id) {
        Details details = detailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Details not found"));

        Map<String, Object> result = new HashMap<>();
        result.put("title", details.getTitle());
        return result;
    }

    public List<Details> getDetailsByTitle(String title){
        return detailsRepository.findByTitle(title);
    }
}
