package bg.softuni.regalcinema.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cinemas")
public class Cinema extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, name = "phone_number")
    private String phoneNumber;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(nullable = false, name = "working_time")
    private String workingTime;

    @ManyToMany(mappedBy = "cinemas")
    private List<Program> programs;

    public Cinema() {
        this.programs = new ArrayList<>();
    }

    public Cinema(String name, String location, String phoneNumber, String workingTime, String description, String imageUrl) {
        this();
        this.description = description;
        this.imageUrl = imageUrl;
        this.location = location;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.workingTime = workingTime;
    }

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

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> program) {
        this.programs = program;
    }
}
