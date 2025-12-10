package com.example.springbootserver.characters;
import jakarta.persistence.*;
import com.example.springbootserver.characterAnimeWorks.CharacterAnimeWorks;
import com.example.springbootserver.characterNicknames.CharacterNicknames;
import com.example.springbootserver.personVoiceWorks.PersonVoiceWorks;
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
    private Set<CharacterNicknames> nicknames = new HashSet<>();

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CharacterAnimeWorks> animeWorks = new HashSet<>();

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PersonVoiceWorks> voiceActors = new HashSet<>();

    public Characters() {}

    public Characters(Integer characterMalId, String url, String name, String nameKanji,
                      String image, Integer favorites, String about) {
        this.characterMalId = characterMalId;
        this.url = url;
        this.name = name;
        this.nameKanji = nameKanji;
        this.image = image;
        this.favorites = favorites != null ? favorites : 0;
        this.about = about;
    }

    public Integer getCharacterMalId() {
        return characterMalId;
    }

    public void setCharacterMalId(Integer characterMalId) {
        this.characterMalId = characterMalId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameKanji() {
        return nameKanji;
    }

    public void setNameKanji(String nameKanji) {
        this.nameKanji = nameKanji;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getFavorites() {
        return favorites;
    }

    public void setFavorites(Integer favorites) {
        this.favorites = favorites != null ? favorites : 0;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Set<CharacterNicknames> getNicknames() {
        return nicknames;
    }

    public void setNicknames(Set<CharacterNicknames> nicknames) {
        this.nicknames = nicknames;
    }

    public Set<CharacterAnimeWorks> getAnimeWorks() {
        return animeWorks;
    }

    public void setAnimeWorks(Set<CharacterAnimeWorks> animeWorks) {
        this.animeWorks = animeWorks;
    }

    public Set<PersonVoiceWorks> getVoiceActors() {
        return voiceActors;
    }

    public void setVoiceActors(Set<PersonVoiceWorks> voiceActors) {
        this.voiceActors = voiceActors;
    }
}
