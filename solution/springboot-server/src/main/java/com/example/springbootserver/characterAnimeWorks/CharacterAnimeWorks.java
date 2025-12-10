package com.example.springbootserver.characterAnimeWorks;

import com.example.springbootserver.characters.Characters;
import com.example.springbootserver.details.Details;
import jakarta.persistence.*;

@Entity
@Table(name = "character_anime_works")
public class CharacterAnimeWorks {

    @EmbeddedId
    private CharacterAnimeWorksId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("animeMalId")
    @JoinColumn(name = "anime_mal_id")
    private Details anime;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("characterMalId")
    @JoinColumn(name = "character_mal_id")
    private Characters character;

    @Column(name = "character_name")
    private String characterName;

    @Column(nullable = false)
    private String role;

    public CharacterAnimeWorks() {}

    public CharacterAnimeWorks(Details anime, Characters character, String characterName, String role) {
        this.anime = anime;
        this.character = character;
        this.characterName = characterName;
        this.role = role;
        if (anime != null && character != null) {
            this.id = new CharacterAnimeWorksId(anime.getMalId(), character.getCharacterMalId());
        }
    }

    public CharacterAnimeWorksId getId() {
        return id;
    }

    public void setId(CharacterAnimeWorksId id) {
        this.id = id;
    }

    public Details getAnime() {
        return anime;
    }

    public void setAnime(Details anime) {
        this.anime = anime;
        if (this.id == null && anime != null && character != null) {
            this.id = new CharacterAnimeWorksId(anime.getMalId(), character.getCharacterMalId());
        }
    }

    public Characters getCharacter() {
        return character;
    }

    public void setCharacter(Characters character) {
        this.character = character;
        if (this.id == null && anime != null && character != null) {
            this.id = new CharacterAnimeWorksId(anime.getMalId(), character.getCharacterMalId());
        }
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
