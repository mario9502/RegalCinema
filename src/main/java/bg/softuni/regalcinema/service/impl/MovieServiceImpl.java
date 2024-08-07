package bg.softuni.regalcinema.service.impl;

import bg.softuni.regalcinema.model.Program;
import bg.softuni.regalcinema.model.ProgramMovieEntity;
import bg.softuni.regalcinema.model.dtos.exportDtos.MovieInfoDto;
import bg.softuni.regalcinema.model.dtos.exportDtos.ShortMovieInfoDto;
import bg.softuni.regalcinema.model.dtos.importDtos.AddMovieDto;
import bg.softuni.regalcinema.repo.ProgramRepository;
import bg.softuni.regalcinema.service.MovieService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private final RestClient restClient;
    private final ProgramRepository programRepository;

    public MovieServiceImpl(RestClient restClient, ProgramRepository programRepository) {
        this.restClient = restClient;
        this.programRepository = programRepository;
    }

    @Override
    public void add(AddMovieDto movieDto) {

        this.restClient
                .post()
                .uri("/movies/add")
                .body(movieDto)
                .retrieve();
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
        List<Program> allPrograms = programRepository.findAll();
        for (Program program : allPrograms) {

            ProgramMovieEntity toRemove = new ProgramMovieEntity();

            List<ProgramMovieEntity> movies = program.getMovies();
            for (ProgramMovieEntity movie : movies) {
                if (movie.getTitle().equals(movieInfoById.getTitle())) {
                    toRemove = movie;
                }
            }

            program.removeMovie(toRemove);
        }

        this.restClient
                .delete()
                .uri("/movies/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve();
    }
}
