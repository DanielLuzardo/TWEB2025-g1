package com.example.springbootserver.personVoiceWorks;


import com.example.springbootserver.characterAnimeWorks.CharacterAnimeWorks;
import com.example.springbootserver.characterAnimeWorks.CharacterAnimeWorksId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonVoiceWorksRepository extends JpaRepository<PersonVoiceWorks, PersonVoiceWorksId> {

    List<PersonVoiceWorks> findByPerson_PersonMalId(Integer id);

    List<PersonVoiceWorks> findByCharacter_CharacterMalId(Integer characterId);

}
