package bg.softuni.regalcinema.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "program_movie_entities")
public class ProgramMovieEntity extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String imageUrl;

    private LocalDate premiere;

    @Column(nullable = false)
    private String startHours;

    @Column(nullable = false)
    private double ticketPrice;

    public ProgramMovieEntity() {}

    public ProgramMovieEntity(String title, String imageUrl, LocalDate premiere, String startHours, double ticketPrice) {
        this.imageUrl = imageUrl;
        this.premiere = premiere;
        this.startHours = startHours;
        this.ticketPrice = ticketPrice;
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDate getPremiere() {
        return premiere;
    }

    public void setPremiere(LocalDate premiere) {
        this.premiere = premiere;
    }

    public String getStartHours() {
        return startHours;
    }

    public void setStartHours(String startHour) {
        this.startHours = startHour;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
