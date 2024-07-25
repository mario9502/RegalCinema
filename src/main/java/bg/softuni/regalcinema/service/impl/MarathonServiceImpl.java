package bg.softuni.regalcinema.service.impl;

import bg.softuni.regalcinema.model.Marathon;
import bg.softuni.regalcinema.model.Movie;
import bg.softuni.regalcinema.model.dtos.AddMarathonDto;
import bg.softuni.regalcinema.repo.MarathonRepository;
import bg.softuni.regalcinema.repo.MovieRepository;
import bg.softuni.regalcinema.service.MarathonService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarathonServiceImpl implements MarathonService {

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

        Marathon marathon = modelMapper.map(marathonDto, Marathon.class);
        //TODO make the modelMapper map the movies list properly

        marathon.setMovies(new ArrayList<>());
        marathonDto.getMovies().forEach(movie -> {
            Movie optMovie = movieRepository.findByTitle(movie).orElse(null);
            marathon.addMovie(optMovie);
        });

//        marathon.setMovies(
//                marathonDto.getMovies()
//                .stream()
//                .map(movie -> movieRepository.findByTitle(movie).orElse(null))
//                .toList()
//        );

        return true;
    }
}
