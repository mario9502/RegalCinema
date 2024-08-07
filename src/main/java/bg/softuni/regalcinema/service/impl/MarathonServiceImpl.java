package bg.softuni.regalcinema.service.impl;

import bg.softuni.regalcinema.model.Marathon;
import bg.softuni.regalcinema.model.dtos.importDtos.AddMarathonDto;
import bg.softuni.regalcinema.repo.MarathonRepository;
import bg.softuni.regalcinema.service.MarathonService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MarathonServiceImpl implements MarathonService {

    private static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm";

    private final MarathonRepository marathonRepository;
    private final MovieServiceImpl movieService;
    private final ModelMapper modelMapper;

    public MarathonServiceImpl(MarathonRepository marathonRepository, MovieServiceImpl movieService, ModelMapper modelMapper) {
        this.marathonRepository = marathonRepository;
        this.movieService = movieService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void add(AddMarathonDto marathonDto) {

        if (marathonRepository.existsByName(marathonDto.getName())) {
            throw new IllegalArgumentException("Marathon already exists");
        }

        Marathon marathon = modelMapper.map(marathonDto, Marathon.class);
        marathon.setMovies(new ArrayList<>());

        this.marathonRepository.save(marathon);

        this.marathonRepository.save(marathon);
    }


}
