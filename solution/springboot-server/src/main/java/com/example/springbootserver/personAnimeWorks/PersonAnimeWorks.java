package com.example.springbootserver.personAnimeWorks;
import com.example.springbootserver.details.Details;
import com.example.springbootserver.personDetails.PersonDetails;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "person_anime_works")
public class PersonAnimeWorks {

    @EmbeddedId
    private PersonAnimeWorksId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("personMalId")
    @JoinColumn(name = "person_mal_id")
    @JsonBackReference
    private PersonDetails person;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("animeMalId")
    @JoinColumn(name = "anime_mal_id")
    @JsonBackReference
    private Details anime;

    public PersonAnimeWorks() {}

    public PersonAnimeWorks(PersonDetails person, Details anime, String position) {
        this.person = person;
        this.anime = anime;
        if (person != null && anime != null) {
            this.id = new PersonAnimeWorksId(
                    person.getPersonMalId(),
                    anime.getMalId(),
                    position
            );
        }
    }

    public PersonAnimeWorksId getId() {
        return id;
    }

    public void setId(PersonAnimeWorksId id) {
        this.id = id;
    }

    public PersonDetails getPerson() {
        return person;
    }


    public void setPerson(PersonDetails person) {
        this.person = person;
    }

    public Details getAnime() {
        return anime;
    }

    public void setAnime(Details anime) {
        this.anime = anime;
    }
}

