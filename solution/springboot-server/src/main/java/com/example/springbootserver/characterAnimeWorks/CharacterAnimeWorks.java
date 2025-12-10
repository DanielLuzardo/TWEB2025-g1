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
}
