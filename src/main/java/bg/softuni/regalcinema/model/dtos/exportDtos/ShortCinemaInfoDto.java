package bg.softuni.regalcinema.model.dtos.exportDtos;

public class ShortCinemaInfoDto {

    private long id;

    private String name;

    private String imageUrl;

    public ShortCinemaInfoDto() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
