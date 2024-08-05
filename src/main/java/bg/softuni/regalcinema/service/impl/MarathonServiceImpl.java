package bg.softuni.regalcinema.service.impl;

import bg.softuni.regalcinema.model.Marathon;
import bg.softuni.regalcinema.model.Movie;
import bg.softuni.regalcinema.model.dtos.importDtos.AddMarathonDto;
import bg.softuni.regalcinema.repo.MarathonRepository;
import bg.softuni.regalcinema.repo.MovieRepository;
import bg.softuni.regalcinema.service.MarathonService;
import bg.softuni.regalcinema.service.exception.ObjectNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class MarathonServiceImpl implements MarathonService {

    private static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm";

    private final MarathonRepository marathonRepository;
    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    public MarathonServiceImpl(MarathonRepository marathonRepository, MovieRepository movieRepository, ModelMapper modelMapper) {
        this.marathonRepository = marathonRepository;
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void add(AddMarathonDto marathonDto) {

        if (marathonRepository.existsByName(marathonDto.getName())) {
            throw new IllegalArgumentException("Marathon already exists");
        }

//        TypeMap<AddMarathonDto, Marathon> typeMap = modelMapper.createTypeMap(AddMarathonDto.class, Marathon.class);
//        typeMap.addMappings(mapper -> mapper.skip((marathon, o) -> marathon.setMovies(List.of())));
//        Marathon marathon = typeMap.map(marathonDto);

        Marathon marathon = modelMapper.map(marathonDto, Marathon.class);

        marathon.setMovies(new ArrayList<>());
        addMovies(marathonDto, marathon);

        this.marathonRepository.save(marathon);

//        marathon.setMovies(
//                marathonDto.getMovies()
//                .stream()
//                .map(movie -> movieRepository.findByTitle(movie).orElse(null))
//                .toList()
//        );
    }

    public void addMovies(AddMarathonDto marathonDto, Marathon marathon) {

        Arrays.stream(marathonDto.getMovies().split(", ")).forEach(movie -> {
            Movie optMovie = movieRepository.findByTitle(movie).orElseThrow(() -> new ObjectNotFoundException("Movie"));
            //TODO implement the REST API exception handler
            marathon.addMovie(optMovie);
        });
    }
}
