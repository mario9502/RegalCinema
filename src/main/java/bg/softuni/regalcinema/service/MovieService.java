package bg.softuni.regalcinema.service;

import bg.softuni.regalcinema.model.dtos.AddMovieDto;

public interface MovieService {
    void add(AddMovieDto movieDto);

    void getMovieInfo(Long id);

    void getAll();
}
