package bg.softuni.regalcinema.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "programs")
public class Program extends BaseEntity {

    @ManyToMany
    private List<Cinema> cinemas;

    @OneToMany
    private List<ProgramMovieEntity> movies;

//    @Column(name = "movies_info", nullable = false)
//    private String moviesInfo;

    @Column(nullable = false)
    private LocalDate date;

    public Program() {
        this.cinemas = new ArrayList<>();
        this.movies = new ArrayList<>();
    }

    public List<Cinema> getCinemas() {
        return cinemas;
    }

    public void setCinemas(List<Cinema> cinemas) {
        this.cinemas = cinemas;
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

    public List<ProgramMovieEntity> getMovies() {
        return movies;
    }

    public void setMovies(List<ProgramMovieEntity> movies) {
        this.movies = movies;
    }

    public void addMovie(ProgramMovieEntity movie){
        this.movies.add(movie);
    }

    public void removeMovie(ProgramMovieEntity movie){
        this.movies.remove(movie);
    }
}
