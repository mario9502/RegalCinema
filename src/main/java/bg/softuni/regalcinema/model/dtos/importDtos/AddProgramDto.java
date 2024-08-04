package bg.softuni.regalcinema.model.dtos.importDtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddProgramDto {

    private List<String> cinemas;

    @NotBlank
    private String moviesInfo;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    public AddProgramDto() {
        this.cinemas = new ArrayList<>();
    }

    public List<String> getCinemas() {
        return cinemas;
    }

    public void setCinemas(List<String> cinemas) {
        this.cinemas = cinemas;
    }

    public String getMoviesInfo() {
        return moviesInfo;
    }

    public void setMoviesInfo(String moviesInfo) {
        this.moviesInfo = moviesInfo;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
