package com.example.springbootserver.details;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailsRepository extends JpaRepository<Details, Integer> {
    List<Details> findByTitleContainingIgnoreCase(String title);

}
