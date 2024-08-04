package bg.softuni.regalcinema.service;

import bg.softuni.regalcinema.model.dtos.importDtos.AddCinemaDto;

public interface CinemaService {
    boolean add(AddCinemaDto addCinemaDto);

    void init();
}
