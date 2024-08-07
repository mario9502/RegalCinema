package bg.softuni.regalcinema.model.dtos.importDtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddProgramDto {

    @NotEmpty(message = "Must select a cinema")
    private List<String> cinemas;

    @NotBlank(message = "Movies info is required")
    private String moviesInfo;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "Program date is required")
    private LocalDate date;

    public AddProgramDto() {
        this.cinemas = new ArrayList<>();
    }

    public AddProgramDto(List<String> cinemas, String moviesInfo, LocalDate date) {
        this.cinemas = cinemas;
        this.moviesInfo = moviesInfo;
        this.date = date;
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
