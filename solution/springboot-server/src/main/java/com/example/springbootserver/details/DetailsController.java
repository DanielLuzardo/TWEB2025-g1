package com.example.springbootserver.details;

import java.util.List;


import com.example.springbootserver.details.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/details")
public class DetailsController {
    private final DetailsService detailsService;

    @Autowired
    public DetailsController(DetailsService detailsService){

        this.detailsService = detailsService;
    }

    @GetMapping("/{id}")
    public Details getDetailsById(@PathVariable Integer id){
        return detailsService.getDetailsById(id);
    }
    /*
    @GetMapping
    public List<Details> getDetailsByTitle(@RequestParam String title){
        return detailsService.getDetailsByTitle(title);
    }
    */
}