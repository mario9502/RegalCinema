package bg.softuni.regalcinema.service.impl;

import bg.softuni.regalcinema.model.dtos.AddMovieDto;
import bg.softuni.regalcinema.model.dtos.ProgramMovieInfoDto;
import bg.softuni.regalcinema.repo.MovieRepository;
import bg.softuni.regalcinema.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;
    private final RestClient restClient;

    public MovieServiceImpl(MovieRepository movieRepository, ModelMapper modelMapper, RestClient restClient) {
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
        this.restClient = restClient;
    }

    @Override
    public void add(AddMovieDto movieDto) {

        this.restClient
                .post()
                .uri("/movies/add")
                .body(movieDto)
                .retrieve();
//        if (movieRepository.existsByTitle(movieDto.getTitle())) {
//            return false;
//        }
//
//        Movie mappedMovie = modelMapper.map(movieDto, Movie.class);
//
//        movieRepository.save(mappedMovie);
//
//        return true;
    }

    @Override
    public void getMovieInfo(Long id) {

        ProgramMovieInfoDto body = this.restClient
                .get()
                .uri("/movies/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(ProgramMovieInfoDto.class);

        System.out.println();
    }

    @Override
    public void getAll() {

        List<ProgramMovieInfoDto> list = this.restClient
                .get()
                .uri("/movies/all")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });

        System.out.println();
    }
}
