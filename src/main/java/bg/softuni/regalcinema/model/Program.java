package bg.softuni.regalcinema.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "programs")
public class Program extends BaseEntity {

    @OneToMany(mappedBy = "program")
    private List<Cinema> cinemas;

    @Column(name = "movies_info", nullable = false)
    private String moviesInfo;

    @Column(nullable = false, unique = true)
    private LocalDate date;

    public Program() {this.cinemas = new ArrayList<>();}

    public List<Cinema> getCinemas() {
        return cinemas;
    }

    public void setCinemas(List<Cinema> cinemas) {
        this.cinemas = cinemas;
    }

    public String getMoviesInfo() {
        return moviesInfo;
    }

    public void setMoviesInfo(String moviesInfo) {
        this.moviesInfo = moviesInfo;
    }

    public void addCinema(Cinema cinema){
        this.cinemas.add(cinema);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
