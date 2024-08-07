package bg.softuni.regalcinema.model.dtos.importDtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

public class AddMarathonDto {

    private static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm";

    @NotBlank
    @Size(min = 5, max = 100)
    private String name;

    @NotBlank
    @Size(min = 5, max = 150)
    private String location;

    @JsonFormat(pattern = DATE_TIME_FORMAT)
    private LocalDateTime start;

    @JsonFormat(pattern = DATE_TIME_FORMAT)
    private LocalDateTime end;

    @NotNull
    private List<String> movies;

    @Size(min = 15, max = 300)
    private String description;

    @NotNull
    @PositiveOrZero
    private double price;

    public AddMarathonDto() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
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

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
