package com.example.springbootserver.characters;
import jakarta.persistence.*;
import com.example.springbootserver.characterAnimeWorks.CharacterAnimeWorks;
import java.lang.String;
import java.util.*;

@Entity
@Table(name = "characters")
public class Characters {

    @Id
    @Column(name = "character_mal_id")
    private Integer characterMalId;

    @Column(length = 500)
    private String url;

    @Column(nullable = false, length = 300)
    private String name;

    @Column(name = "name_kanji", columnDefinition = "TEXT")
    private String nameKanji;

    @Column(length = 500)
    private String image;

    @Column(nullable = false)
    private Integer favorites = 0;

    @Column(columnDefinition = "TEXT")
    private String about;

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CharacterNickname> nicknames = new HashSet<>();

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CharacterAnimeWorks> animeWorks = new HashSet<>();

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PersonVoiceWork> voiceActors = new HashSet<>();
}
