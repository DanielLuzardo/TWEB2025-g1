package com.example.springbootserver.personAnimeWorks;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonAnimeWorksRepository extends JpaRepository<PersonAnimeWorks, PersonAnimeWorksId> {
    List<PersonAnimeWorks> findByPerson_PersonMalId(Integer id);
}
