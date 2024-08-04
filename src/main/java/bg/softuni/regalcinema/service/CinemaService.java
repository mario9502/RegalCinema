package bg.softuni.regalcinema.service;

import bg.softuni.regalcinema.model.dtos.exportDtos.CinemaInfoDto;
import bg.softuni.regalcinema.model.dtos.exportDtos.ShortCinemaInfoDto;
import bg.softuni.regalcinema.model.dtos.importDtos.AddCinemaDto;

import java.util.List;

public interface CinemaService {
    boolean add(AddCinemaDto addCinemaDto);

    void init();

    List<ShortCinemaInfoDto> getAll();

    CinemaInfoDto findById(Long id);

    void delete(Long id);
}
