package com.example.springbootserver.personVoiceWorks;
import com.example.springbootserver.details.Details;
import com.example.springbootserver.personDetails.PersonDetails;
import com.example.springbootserver.characters.Characters;
import jakarta.persistence.*;


@Entity
@Table(name = "person_voice_works")
public class PersonVoiceWorks {

    @EmbeddedId
    private PersonVoiceWorksId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("personMalId")
    @JoinColumn(name = "person_mal_id")
    private PersonDetails person;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("animeMalId")
    @JoinColumn(name = "anime_mal_id")
    private Details anime;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("characterMalId")
    @JoinColumn(name = "character_mal_id")
    private Characters character;

    @Column(nullable = false, length = 50)
    private String role;

    @Column(nullable = false, length = 50)
    private String language;

    public PersonVoiceWorks() {}

    // Constructor completo
    public PersonVoiceWorks(PersonDetails person, Details anime, Characters character, String role, String language) {
        this.person = person;
        this.anime = anime;
        this.character = character;
        this.role = role;
        this.language = language;

        if (person != null && anime != null && character != null) {
            this.id = new PersonVoiceWorksId(person.getPersonMalId(), anime.getMalId(), character.getCharacterMalId());
        }
    }

    // Getters y setters
    public PersonVoiceWorksId getId() {
        return id;
    }

    public void setId(PersonVoiceWorksId id) {
        this.id = id;
    }

    public PersonDetails getPerson() {
        return person;
    }

    public void setPerson(PersonDetails person) {
        this.person = person;
        if (this.id != null && person != null && anime != null && character != null) {
            this.id.setPersonMalId(person.getPersonMalId());
        }
    }

    public Details getAnime() {
        return anime;
    }

    public void setAnime(Details anime) {
        this.anime = anime;
        if (this.id != null && person != null && anime != null && character != null) {
            this.id.setAnimeMalId(anime.getMalId());
        }
    }

    public Characters getCharacter() {
        return character;
    }

    public void setCharacter(Characters character) {
        this.character = character;
        if (this.id != null && person != null && anime != null && character != null) {
            this.id.setCharacterMalId(character.getCharacterMalId());
        }
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}

