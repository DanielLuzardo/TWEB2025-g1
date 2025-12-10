package com.example.springbootserver.personAlternateName;
import com.example.springbootserver.personDetails.PersonDetails;
import jakarta.persistence.*;

@Entity
@Table(name = "person_alternate_names")
public class PersonAlternateName {

    @EmbeddedId
    private PersonAlternateNameId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("personMalId")
    @JoinColumn(name = "person_mal_id")
    private PersonDetails person;

    public PersonAlternateName() {}

    public PersonAlternateName(PersonDetails person, String alternateName) {
        this.person = person;
        this.id = new PersonAlternateNameId(
                person != null ? person.getPersonMalId() : null,
                alternateName
        );
    }


    public PersonAlternateNameId getId() {
        return id;
    }

    public void setId(PersonAlternateNameId id) {
        this.id = id;
    }

    public PersonDetails getPerson() {
        return person;
    }

    public void setPerson(PersonDetails person) {
        this.person = person;
        if (this.id != null) {
            this.id.setPersonMalId(person != null ? person.getPersonMalId() : null);
        }
    }

    public String getAlternateName() {
        return id != null ? id.getAltName() : null;
    }

    public void setAlternateName(String alternateName) {
        if (this.id == null) {
            this.id = new PersonAlternateNameId();
        }
        this.id.setAltName(alternateName);
    }
}