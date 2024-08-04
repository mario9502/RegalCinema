package bg.softuni.regalcinema.model.dtos.exportDtos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProgramMovieInfoDto {

    private String title;

    private String imageUrl;

    private LocalDate premiere;

    private List<String> startHour;

    private double ticketPrice;

    public ProgramMovieInfoDto() {
        this.startHour = new ArrayList<>();
    }

    public ProgramMovieInfoDto(String title, String imageUrl, LocalDate premiere, List<String> startHour, double ticketPrice) {
        this();
        this.title = title;
        this.imageUrl = imageUrl;
        this.premiere = premiere;
        this.startHour = startHour;
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

    public List<String> getStartHour() {
        return startHour;
    }

    public void setStartHour(List<String> startHour) {
        this.startHour = startHour;
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
