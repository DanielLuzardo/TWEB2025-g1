package com.example.springbootserver.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Details> getDetailsByTitle(String title){
        return detailsRepository.findByTitle(title);
    }
}
