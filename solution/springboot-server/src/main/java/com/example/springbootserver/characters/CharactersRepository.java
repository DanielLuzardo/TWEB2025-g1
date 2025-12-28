package com.example.springbootserver.characters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharactersRepository extends JpaRepository<Characters, Integer> {
    List<Characters> findByName(String name);
    @Query("""
        SELECT DISTINCT cw.character
        FROM CharacterAnimeWorks cw
        WHERE cw.id.animeMalId = :animeMalId
    """)
    List<Characters> findAllByAnimeMalId(@Param("animeMalId") Integer animeMalId);
}