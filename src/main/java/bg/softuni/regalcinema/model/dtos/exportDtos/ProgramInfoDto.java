package bg.softuni.regalcinema.model.dtos.exportDtos;

import bg.softuni.regalcinema.model.Cinema;

import java.time.LocalDate;
import java.util.List;

public class ProgramInfoDto {

    private List<Cinema> cinemas;

    private String moviesInfo;

    private LocalDate date;

    public ProgramInfoDto() {}

    public List<Cinema> getCinemas() {
        return cinemas;
    }

    public void setCinemas(List<Cinema> cinemas) {
        this.cinemas = cinemas;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getMoviesInfo() {
        return moviesInfo;
    }

    public void setMoviesInfo(String moviesInfo) {
        this.moviesInfo = moviesInfo;
    }
}
