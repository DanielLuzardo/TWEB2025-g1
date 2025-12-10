package com.example.springbootserver.personVoiceWorks;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PersonVoiceWorksId implements java.io.Serializable {
    @Column(name = "person_mal_id")
    private Integer personMalId;

    @Column(name = "anime_mal_id")
    private Integer animeMalId;

    @Column(name = "character_mal_id")
    private Integer characterMalId;

    public PersonVoiceWorksId() {}

    public PersonVoiceWorksId(Integer personMalId, Integer animeMalId, Integer characterMalId) {
        this.personMalId = personMalId;
        this.animeMalId = animeMalId;
        this.characterMalId = characterMalId;
    }

    public Integer getPersonMalId() {
        return personMalId;
    }

    public void setPersonMalId(Integer personMalId) {
        this.personMalId = personMalId;
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