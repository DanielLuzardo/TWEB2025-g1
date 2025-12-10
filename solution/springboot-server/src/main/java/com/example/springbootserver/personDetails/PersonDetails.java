package com.example.springbootserver.personDetails;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.lang.String;
import java.util.*;

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

    // Relaciones
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PersonAlternateName> alternateNames = new HashSet<>();

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PersonAnimeWork> animeWorks = new HashSet<>();

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PersonVoiceWork> voiceWorks = new HashSet<>();
}