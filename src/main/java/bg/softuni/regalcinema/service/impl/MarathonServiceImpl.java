package bg.softuni.regalcinema.service.impl;

import bg.softuni.regalcinema.model.Marathon;
import bg.softuni.regalcinema.model.Movie;
import bg.softuni.regalcinema.model.dtos.AddMarathonDto;
import bg.softuni.regalcinema.repo.MarathonRepository;
import bg.softuni.regalcinema.repo.MovieRepository;
import bg.softuni.regalcinema.service.MarathonService;
import org.modelmapper.AbstractProvider;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

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
    public boolean add(AddMarathonDto marathonDto) {

        if (marathonRepository.existsByName(marathonDto.getName())) {
            return false; //TODO throw an exception if marathon with the same name already exists
        }

//        TypeMap<AddMarathonDto, Marathon> typeMap = modelMapper.createTypeMap(AddMarathonDto.class, Marathon.class);
//        typeMap.addMappings(mapper -> mapper.skip((marathon, o) -> marathon.setMovies(List.of())));
//        Marathon marathon = typeMap.map(marathonDto);

        Marathon marathon = modelMapper.map(marathonDto, Marathon.class); //TODO try to make the modelMapper .skip work on movies list

        marathon.setMovies(new ArrayList<>());
        addMovies(marathonDto, marathon);

        this.marathonRepository.save(marathon);

        return true;
//        marathon.setMovies(
//                marathonDto.getMovies()
//                .stream()
//                .map(movie -> movieRepository.findByTitle(movie).orElse(null))
//                .toList()
//        );
    }

    public void addMovies(AddMarathonDto marathonDto, Marathon marathon) {
        marathonDto.getMovies().forEach(movie -> {
            Movie optMovie = movieRepository.findByTitle(movie).orElse(null); //TODO throw and exception instead of null
            marathon.addMovie(optMovie);
        });
    }
}
