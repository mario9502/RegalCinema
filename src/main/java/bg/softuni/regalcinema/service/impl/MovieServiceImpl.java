package bg.softuni.regalcinema.service.impl;

import bg.softuni.regalcinema.model.Movie;
import bg.softuni.regalcinema.model.dtos.AddMovieDto;
import bg.softuni.regalcinema.repo.MovieRepository;
import bg.softuni.regalcinema.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    public MovieServiceImpl(MovieRepository movieRepository, ModelMapper modelMapper) {
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean add(AddMovieDto movieDto) {

        if (movieRepository.existsByTitle(movieDto.getTitle())) {
            return false;
        }

        Movie mappedMovie = modelMapper.map(movieDto, Movie.class);

        LocalDate premiereDate = LocalDate.parse(movieDto.getPremiere(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        mappedMovie.setPremiere(premiereDate);

        movieRepository.save(mappedMovie);

        return true;

    }
}
