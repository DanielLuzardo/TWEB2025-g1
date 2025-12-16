package com.example.springbootserver.characterNicknames;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterNicknamesRepository extends JpaRepository<CharacterNicknames,CharacterNicknameId> {
    List<CharacterNicknames> findByCharacter_CharacterMalId(Integer characterMalId);

}
