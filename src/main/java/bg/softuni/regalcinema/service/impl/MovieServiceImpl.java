package bg.softuni.regalcinema.service.impl;

import bg.softuni.regalcinema.model.dtos.exportDtos.MovieInfoDto;
import bg.softuni.regalcinema.model.dtos.exportDtos.ShortMovieInfoDto;
import bg.softuni.regalcinema.model.dtos.importDtos.AddMovieDto;
import bg.softuni.regalcinema.model.dtos.exportDtos.ProgramMovieInfoDto;
import bg.softuni.regalcinema.repo.MovieRepository;
import bg.softuni.regalcinema.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private final RestClient restClient;

    public MovieServiceImpl(RestClient restClient) {
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
    public MovieInfoDto getMovieInfoById(Long id) {

        return this.restClient
                .get()
                .uri("/movies/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(MovieInfoDto.class);
    }

    @Override
    public MovieInfoDto getMovieInfoByTitle(String title) {

        return this.restClient
                .get()
                .uri("/movies/title={title}", title)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(MovieInfoDto.class);
    }

    @Override
    public List<ShortMovieInfoDto> getAllShortInfo() {

        return this.restClient
                .get()
                .uri("/movies/all")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    @Override
    public void deleteById(Long id) {

        MovieInfoDto movieInfoById = getMovieInfoById(id);

        this.restClient
                .delete()
                .uri("/movies/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve();
    }
}
