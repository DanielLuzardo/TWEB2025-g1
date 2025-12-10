package com.example.springbootserver.personDetails;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.lang.String;
import java.util.*;
import com.example.springbootserver.personAlternateName.PersonAlternateName;
import com.example.springbootserver.personAnimeWorks.PersonAnimeWorks;
import com.example.springbootserver.personVoiceWorks.PersonVoiceWorks;

@Entity
@Table(name = "person_details")
public class PersonDetails {

    @Id
    @Column(name = "person_mal_id")
    private Integer personMalId;

    @Column(length = 500)
    private String url;

    @Column(name = "website_url", length = 500)
    private String websiteUrl;

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Column(nullable = false, length = 300)
    private String name;

    @Column(name = "given_name", length = 150)
    private String givenName;

    @Column(name = "family_name", length = 150)
    private String familyName;

    private LocalDateTime birthday;

    @Column(nullable = false)
    private Integer favorites = 0;

    @Column(name = "relevant_location", length = 200)
    private String relevantLocation;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PersonAlternateName> alternateNames = new HashSet<>();

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PersonAnimeWorks> animeWorks = new HashSet<>();

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PersonVoiceWorks> voiceWorks = new HashSet<>();

    public PersonDetails() {}

    public PersonDetails(Integer personMalId, String url, String websiteUrl, String imageUrl,
                         String name, String givenName, String familyName, LocalDateTime birthday,
                         Integer favorites, String relevantLocation) {
        this.personMalId = personMalId;
        this.url = url;
        this.websiteUrl = websiteUrl;
        this.imageUrl = imageUrl;
        this.name = name;
        this.givenName = givenName;
        this.familyName = familyName;
        this.birthday = birthday;
        this.favorites = favorites != null ? favorites : 0;
        this.relevantLocation = relevantLocation;
    }

    public Integer getPersonMalId() {
        return personMalId;
    }

    public void setPersonMalId(Integer personMalId) {
        this.personMalId = personMalId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public Integer getFavorites() {
        return favorites;
    }

    public void setFavorites(Integer favorites) {
        this.favorites = favorites != null ? favorites : 0;
    }

    public String getRelevantLocation() {
        return relevantLocation;
    }

    public void setRelevantLocation(String relevantLocation) {
        this.relevantLocation = relevantLocation;
    }

    public Set<PersonAlternateName> getAlternateNames() {
        return alternateNames;
    }

    public void setAlternateNames(Set<PersonAlternateName> alternateNames) {
        this.alternateNames = alternateNames;
    }

    public Set<PersonAnimeWorks> getAnimeWorks() {
        return animeWorks;
    }

    public void setAnimeWorks(Set<PersonAnimeWorks> animeWorks) {
        this.animeWorks = animeWorks;
    }

    public Set<PersonVoiceWorks> getVoiceWorks() {
        return voiceWorks;
    }

    public void setVoiceWorks(Set<PersonVoiceWorks> voiceWorks) {
        this.voiceWorks = voiceWorks;
    }

    public void addAlternateName(PersonAlternateName altName) {
        alternateNames.add(altName);
        altName.setPerson(this);
    }

    public void removeAlternateName(PersonAlternateName altName) {
        alternateNames.remove(altName);
        altName.setPerson(null);
    }

    public void addAnimeWork(PersonAnimeWorks work) {
        animeWorks.add(work);
        work.setPerson(this);
    }

    public void removeAnimeWork(PersonAnimeWorks work) {
        animeWorks.remove(work);
        work.setPerson(null);
    }

    public void addVoiceWork(PersonVoiceWorks work) {
        voiceWorks.add(work);
        work.setPerson(this);
    }

    public void removeVoiceWork(PersonVoiceWorks work) {
        voiceWorks.remove(work);
        work.setPerson(null);
    }
}