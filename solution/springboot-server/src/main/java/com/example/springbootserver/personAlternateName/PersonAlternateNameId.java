package com.example.springbootserver.personAlternateName;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PersonAlternateNameId implements java.io.Serializable {
    @Column(name = "person_mal_id")
    private Integer personMalId;

    @Column(name = "alt_name", length = 300)
    private String altName;

    public PersonAlternateNameId() {}

    public PersonAlternateNameId(Integer personMalId, String altName) {
        this.personMalId = personMalId;
        this.altName = altName;
    }

    public Integer getPersonMalId() {
        return personMalId;
    }

    public void setPersonMalId(Integer personMalId) {
        this.personMalId = personMalId;
    }

    public String getAltName() {
        return altName;
    }

    public void setAltName(String altName) {
        this.altName = altName;
    }

}
