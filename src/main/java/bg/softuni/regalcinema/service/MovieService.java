package bg.softuni.regalcinema.service;

import bg.softuni.regalcinema.model.dtos.AddMovieDto;

public interface MovieService {
    boolean add(AddMovieDto movieDto);
}
