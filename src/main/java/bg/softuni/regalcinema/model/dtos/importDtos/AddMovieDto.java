package bg.softuni.regalcinema.model.dtos.importDtos;


import bg.softuni.regalcinema.model.enums.Audio;
import bg.softuni.regalcinema.model.enums.Genre;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class AddMovieDto {

    @NotBlank(message = "Title is required")
    @Size(min = 2, max = 50, message = "Size must be between 2 and 50 symbols")
    private String title;

    @NotBlank(message = "Director name is required")
    @Size(min = 3, max = 50, message = "Size must be between 3 and 50 symbols")
    private String directorName;

    @Size(min = 10, max = 100, message = "Size must be between 10 and 100 symbols")
    private String actors;

    @NotBlank(message = "Description is required")
    @Size(min = 20, max = 200, message = "Size must be between 20 and 200 symbols")
    private String description;

    @NotNull(message = "Length is required")
    @Positive(message = "Length must be valid")
    private int lengthInMinutes;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate premiere;

    @Size(min = 10, max = 200, message = "Size must be between 10 and 200 symbols")
    private String imageUrl;

    @NotNull(message = "Audio is required")
    private Audio audio;

    @NotNull(message = "Genre is required")
    private Genre genre;

    public AddMovieDto() {}

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

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
