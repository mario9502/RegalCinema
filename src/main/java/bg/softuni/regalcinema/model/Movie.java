package bg.softuni.regalcinema.model;

import bg.softuni.regalcinema.model.enums.Audio;
import bg.softuni.regalcinema.model.enums.Genre;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "movies")
public class Movie extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false, name = "director_name")
    private String directorName;

    @Basic
    private String actors;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, name = "length_in_minutes")
    private int lengthInMinutes;

    @Basic
    private LocalDate premiere;

    @Column(name = "image_url")
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Audio audio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genre genre;

    public Movie() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public void setLengthInMinutes(int lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }

    public LocalDate getPremiere() {
        return premiere;
    }

    public void setPremiere(LocalDate premiere) {
        this.premiere = premiere;
    }

    public Audio getAudio() {
        return audio;
    }

    public void setAudio(Audio audio) {
        this.audio = audio;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
