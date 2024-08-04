package bg.softuni.regalcinema.service;

import bg.softuni.regalcinema.model.dtos.exportDtos.MovieInfoDto;
import bg.softuni.regalcinema.model.dtos.exportDtos.ShortMovieInfoDto;
import bg.softuni.regalcinema.model.dtos.importDtos.AddMovieDto;

import java.util.List;

public interface MovieService {
    void add(AddMovieDto movieDto);

    MovieInfoDto getMovieInfoById(Long id);

    MovieInfoDto getMovieInfoByTitle(String title);

    List<ShortMovieInfoDto> getAllShortInfo();

    void deleteById(Long id);
}