package bg.softuni.regalcinema.model.dtos;

import bg.softuni.regalcinema.model.Movie;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

public class AddMarathonDto {

    @NotBlank
    @Size(min = 5, max = 100)
    private String name;

    @NotBlank
    @Size(min = 5, max = 150)
    private String location;

    @NotBlank
    @Size(min = 5, max = 100)
    private String start;

    @NotBlank
    @Size(min = 5, max = 100)
    private String end;

    private List<String> movies;

    @Size(min = 15, max = 300)
    private String description;

    public AddMarathonDto() {
        this.movies = new ArrayList<>();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getMovies() {
        return movies;
    }

    public void setMovies(List<String> movies) {
        this.movies = movies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }
}
