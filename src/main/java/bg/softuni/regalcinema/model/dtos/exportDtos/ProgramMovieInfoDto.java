package bg.softuni.regalcinema.model.dtos.exportDtos;

import java.time.LocalDate;

public class ProgramMovieInfoDto {

    private String title;

    private String imageUrl;

    private LocalDate premiere;

    private String startHours;

    private double ticketPrice;

    public ProgramMovieInfoDto() {}

    public ProgramMovieInfoDto(String title, String imageUrl, LocalDate premiere, String startHours, double ticketPrice) {
        this();
        this.title = title;
        this.imageUrl = imageUrl;
        this.premiere = premiere;
        this.startHours = startHours;
        this.ticketPrice = ticketPrice;
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

    public String getStartHours() {
        return startHours;
    }

    public void setStartHours(String startHours) {
        this.startHours = startHours;
    }
}
