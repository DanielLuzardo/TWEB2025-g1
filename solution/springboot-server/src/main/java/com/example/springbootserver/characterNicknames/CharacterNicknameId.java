package com.example.springbootserver.characterNicknames;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CharacterNicknameId implements java.io.Serializable {
    @Column(name = "character_mal_id")
    private Integer characterMalId;

    @Column(length = 300)
    private String nickname;

    public CharacterNicknameId() {}

    public CharacterNicknameId(Integer characterMalId, String nickname) {
        this.characterMalId = characterMalId;
        this.nickname = nickname;
    }

    public Integer getCharacterMalId() {
        return characterMalId;
    }

    public void setCharacterMalId(Integer characterMalId) {
        this.characterMalId = characterMalId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}