package com.example.springbootserver.details;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.lang.String;
import java.util.*;

import jakarta.persistence.CascadeType;



@Entity
@Table(name = "details")
public class Details {

    @Id
    @Column(name = "mal_id")
    private Integer malId;

    @Column(name = "title", nullable = false, length = 500)
    private String title;

    @Column(name = "title_japanese", length = 500)
    private String titleJapanese;

    @Column(length = 500)
    private String url;

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Column(length = 50)
    private String type;

    @Column(length = 50)
    private String status;

    @Column
    private Double score;

    @Column(name = "scored_by")
    private Integer scoredBy;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(columnDefinition = "TEXT")
    private String synopsis;

    private Integer rank;

    private Integer popularity;

    private Integer members;

    private Integer favorites;

    @ElementCollection
    @CollectionTable(name = "details_genres", joinColumns = @JoinColumn(name = "mal_id"))
    @Column(name = "genre")
    private List<String> genres = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "details_studios", joinColumns = @JoinColumn(name = "mal_id"))
    @Column(name = "studio")
    private List<String> studios = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "details_themes", joinColumns = @JoinColumn(name = "mal_id"))
    @Column(name = "theme")
    private List<String> themes = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "details_demographics", joinColumns = @JoinColumn(name = "mal_id"))
    @Column(name = "demographic")
    private List<String> demographics = new ArrayList<>();


    @Column(length = 100)
    private String source;

    @Column(length = 100)
    private String rating;

    @Column
    private Integer episodes;

    @Column(length = 50)
    private String season;

    private Integer year;

    @ElementCollection
    @CollectionTable(name = "details_producers", joinColumns = @JoinColumn(name = "mal_id"))
    @Column(name = "producer")
    private List<String> producers = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "details_explicit_genres", joinColumns = @JoinColumn(name = "mal_id"))
    @Column(name = "explicit_genre")
    private List<String> explicitGenres = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "details_licensors", joinColumns = @JoinColumn(name = "mal_id"))
    @Column(name = "licensor")
    private List<String> licensors = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "details_streaming", joinColumns = @JoinColumn(name = "mal_id"))
    @Column(name = "streaming_platform")
    private List<String> streaming = new ArrayList<>();

    // Relations
    @OneToMany(mappedBy = "anime", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CharacterAnimeWork> characterWorks = new HashSet<>();

    @OneToMany(mappedBy = "anime", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PersonAnimeWork> personWorks = new HashSet<>();

    @OneToMany(mappedBy = "anime", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PersonVoiceWork> voiceWorks = new HashSet<>();

    public Details() {

    }
}