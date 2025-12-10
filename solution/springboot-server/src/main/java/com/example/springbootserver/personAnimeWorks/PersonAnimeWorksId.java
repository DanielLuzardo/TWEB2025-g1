package com.example.springbootserver.personAnimeWorks;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PersonAnimeWorksId implements java.io.Serializable {
    @Column(name = "person_mal_id")
    private Integer personMalId;

    @Column(name = "anime_mal_id")
    private Integer animeMalId;

    @Column(length = 200)
    private String position;

    public PersonAnimeWorksId() {}

    public PersonAnimeWorksId(Integer personMalId, Integer animeMalId, String position) {
        this.personMalId = personMalId;
        this.animeMalId = animeMalId;
        this.position = position;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}