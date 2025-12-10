package com.example.springbootserver.characterNicknames;
import jakarta.persistence.*;
import com.example.springbootserver.characters.Characters;
import java.lang.String;
import java.util.*;

@Entity
@Table(name = "character_nicknames")
public class CharacterNicknames {

    @EmbeddedId
    private CharacterNicknameId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("characterMalId")
    @JoinColumn(name = "character_mal_id")
    private Characters character;

    public CharacterNicknames() {}

    public CharacterNicknames(Characters character, String nickname) {
        this.character = character;
        this.id = new CharacterNicknameId();
        if (character != null) {
            this.id.setCharacterMalId(character.getCharacterMalId());
        }
        this.id.setNickname(nickname);
    }

    public CharacterNicknameId getId() {
        return id;
    }

    public void setId(CharacterNicknameId id) {
        this.id = id;
    }

    public Characters getCharacter() {
        return character;
    }

    public void setCharacter(Characters character) {
        this.character = character;
        if (this.id != null && character != null) {
            this.id.setCharacterMalId(character.getCharacterMalId());
        }
    }

    public String getNickname() {
        return id != null ? id.getNickname() : null;
    }

    public void setNickname(String nickname) {
        if (this.id == null) {
            this.id = new CharacterNicknameId();
        }
        this.id.setNickname(nickname);
    }
}

