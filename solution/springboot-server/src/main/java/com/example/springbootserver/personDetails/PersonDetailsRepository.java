package com.example.springbootserver.personDetails;


import com.example.springbootserver.characters.Characters;
import com.example.springbootserver.details.Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonDetailsRepository extends JpaRepository<PersonDetails, Integer> {
    List<PersonDetails> findByName(String name);
}
