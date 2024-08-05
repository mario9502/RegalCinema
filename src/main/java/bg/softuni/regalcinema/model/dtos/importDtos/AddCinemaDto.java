package bg.softuni.regalcinema.model.dtos.importDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AddCinemaDto {

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 30, message = "Size must be between 3 and 30 symbols")
    private String name;

    @NotBlank(message = "Location is required")
    @Size(min = 3, max = 150, message = "Size must be between 3 and 150 symbols")
    private String location;

    @Size(min = 15, max = 300, message = "Size must be between 15 and 300 symbols")
    private String description;

    @NotBlank(message = "Phone number is required")
    @Size(min = 10, max = 13, message = "Size must be between 10 and 13 symbols")
    private String phoneNumber;

    @NotBlank(message = "Working time is required")
    @Size(min = 9, max = 11, message = "Size must be between 9 and 11 symbols")
    private String workingTime;

    @Size(min = 15, max = 200, message = "Size must be between 15 and 200 symbols")
    private String imageUrl;

    public AddCinemaDto() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(String workingTime) {
        this.workingTime = workingTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
