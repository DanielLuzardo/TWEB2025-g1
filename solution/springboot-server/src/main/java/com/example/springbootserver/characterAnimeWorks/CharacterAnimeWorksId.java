package com.example.springbootserver.characterAnimeWorks;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CharacterAnimeWorksId implements Serializable {

    @Column(name = "anime_mal_id")
    private Integer animeMalId;

    @Column(name = "character_mal_id")
    private Integer characterMalId;


    public CharacterAnimeWorksId() {}

    public CharacterAnimeWorksId(Integer animeMalId, Integer characterMalId) {
        this.animeMalId = animeMalId;
        this.characterMalId = characterMalId;
    }

    public Integer getAnimeMalId() {
        return animeMalId;
    }

    public void setAnimeMalId(Integer animeMalId) {
        this.animeMalId = animeMalId;
    }

    public Integer getCharacterMalId() {
        return characterMalId;
    }

    public void setCharacterMalId(Integer characterMalId) {
        this.characterMalId = characterMalId;
    }
}
