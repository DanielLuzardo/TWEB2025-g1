package com.example.springbootserver.details;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.lang.String;
import java.util.*;
import com.example.springbootserver.StringListConverter;
import com.example.springbootserver.characterAnimeWorks.CharacterAnimeWorks;
import com.example.springbootserver.personAnimeWorks.PersonAnimeWorks;
import com.example.springbootserver.personVoiceWorks.PersonVoiceWorks;

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

    @Column(columnDefinition = "TEXT")
    @Convert(converter = StringListConverter.class)
    private List<String> genres = new ArrayList<>();

    @Column(columnDefinition = "TEXT")
    @Convert(converter = StringListConverter.class)
    private List<String> studios = new ArrayList<>();

    @Column(columnDefinition = "TEXT")
    @Convert(converter = StringListConverter.class)
    private List<String> themes = new ArrayList<>();

    @Column(columnDefinition = "TEXT")
    @Convert(converter = StringListConverter.class)
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

    @Column(columnDefinition = "TEXT")
    @Convert(converter = StringListConverter.class)
    private List<String> producers = new ArrayList<>();

    @Column(columnDefinition = "TEXT")
    @Convert(converter = StringListConverter.class)
    private List<String> explicitGenres = new ArrayList<>();

    @Column(columnDefinition = "TEXT")
    @Convert(converter = StringListConverter.class)
    private List<String> licensors = new ArrayList<>();

    @Column(columnDefinition = "TEXT")
    @Convert(converter = StringListConverter.class)
    private List<String> streaming = new ArrayList<>();

    // Relations
    @OneToMany(mappedBy = "anime", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<CharacterAnimeWorks> characterWorks = new HashSet<>();

    @OneToMany(mappedBy = "anime", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<PersonAnimeWorks> personWorks = new HashSet<>();

    @OneToMany(mappedBy = "anime", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<PersonVoiceWorks> voiceWorks = new HashSet<>();

    public Details() {}

    public Details(Integer malId, String title, String titleJapanese, String url, String imageUrl,
                   String type, String status, Double score, Integer scoredBy, LocalDateTime startDate,
                   LocalDateTime endDate, String synopsis, Integer rank, Integer popularity,
                   Integer members, Integer favorites, String source, String rating, Integer episodes,
                   String season, Integer year) {
        this.malId = malId;
        this.title = title;
        this.titleJapanese = titleJapanese;
        this.url = url;
        this.imageUrl = imageUrl;
        this.type = type;
        this.status = status;
        this.score = score;
        this.scoredBy = scoredBy;
        this.startDate = startDate;
        this.endDate = endDate;
        this.synopsis = synopsis;
        this.rank = rank;
        this.popularity = popularity;
        this.members = members;
        this.favorites = favorites;
        this.source = source;
        this.rating = rating;
        this.episodes = episodes;
        this.season = season;
        this.year = year;
    }

    public Integer getMalId() {
        return malId;
    }

    public void setMalId(Integer malId) {
        this.malId = malId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleJapanese() {
        return titleJapanese;
    }

    public void setTitleJapanese(String titleJapanese) {
        this.titleJapanese = titleJapanese;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getScoredBy() {
        return scoredBy;
    }

    public void setScoredBy(Integer scoredBy) {
        this.scoredBy = scoredBy;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public Integer getMembers() {
        return members;
    }

    public void setMembers(Integer members) {
        this.members = members;
    }

    public Integer getFavorites() {
        return favorites;
    }

    public void setFavorites(Integer favorites) {
        this.favorites = favorites;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<String> getStudios() {
        return studios;
    }

    public void setStudios(List<String> studios) {
        this.studios = studios;
    }

    public List<String> getThemes() {
        return themes;
    }

    public void setThemes(List<String> themes) {
        this.themes = themes;
    }

    public List<String> getDemographics() {
        return demographics;
    }

    public void setDemographics(List<String> demographics) {
        this.demographics = demographics;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Integer getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Integer episodes) {
        this.episodes = episodes;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<String> getProducers() {
        return producers;
    }

    public void setProducers(List<String> producers) {
        this.producers = producers;
    }

    public List<String> getExplicitGenres() {
        return explicitGenres;
    }

    public void setExplicitGenres(List<String> explicitGenres) {
        this.explicitGenres = explicitGenres;
    }

    public List<String> getLicensors() {
        return licensors;
    }

    public void setLicensors(List<String> licensors) {
        this.licensors = licensors;
    }

    public List<String> getStreaming() {
        return streaming;
    }

    public void setStreaming(List<String> streaming) {
        this.streaming = streaming;
    }

    public Set<CharacterAnimeWorks> getCharacterWorks() {
        return characterWorks;
    }

    public void setCharacterWorks(Set<CharacterAnimeWorks> characterWorks) {
        this.characterWorks = characterWorks;
    }

    public Set<PersonAnimeWorks> getPersonWorks() {
        return personWorks;
    }

    public void setPersonWorks(Set<PersonAnimeWorks> personWorks) {
        this.personWorks = personWorks;
    }

    public Set<PersonVoiceWorks> getVoiceWorks() {
        return voiceWorks;
    }

    public void setVoiceWorks(Set<PersonVoiceWorks> voiceWorks) {
        this.voiceWorks = voiceWorks;
    }

    public void addCharacterWork(CharacterAnimeWorks work) {
        characterWorks.add(work);
        work.setAnime(this);
    }

    public void removeCharacterWork(CharacterAnimeWorks work) {
        characterWorks.remove(work);
        work.setAnime(null);
    }

    public void addPersonWork(PersonAnimeWorks work) {
        personWorks.add(work);
        work.setAnime(this);
    }

    public void removePersonWork(PersonAnimeWorks work) {
        personWorks.remove(work);
        work.setAnime(null);
    }

    public void addVoiceWork(PersonVoiceWorks work) {
        voiceWorks.add(work);
        work.setAnime(this);
    }

    public void removeVoiceWork(PersonVoiceWorks work) {
        voiceWorks.remove(work);
        work.setAnime(null);
    }
}