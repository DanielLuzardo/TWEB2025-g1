package com.example.springbootserver.characterAnimeWorks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterAnimeWorksRepository extends JpaRepository<CharacterAnimeWorks, CharacterAnimeWorksId> {

    List<CharacterAnimeWorks> findByCharacter_CharacterMalId(Integer person);
}
