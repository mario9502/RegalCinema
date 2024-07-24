package bg.softuni.regalcinema.service.impl;

import bg.softuni.regalcinema.model.Cinema;
import bg.softuni.regalcinema.model.dtos.AddCinemaDto;
import bg.softuni.regalcinema.repo.CinemaRepository;
import bg.softuni.regalcinema.service.CinemaService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CinemaServiceImpl implements CinemaService {

    private final CinemaRepository cinemaRepository;
    private final ModelMapper modelMapper;

    public CinemaServiceImpl(CinemaRepository cinemaRepository, ModelMapper modelMapper) {
        this.cinemaRepository = cinemaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean add(AddCinemaDto addCinemaDto) {

        if (cinemaRepository.existsByName(addCinemaDto.getName())) {
            return false;
        }

        Cinema mappedCinema = modelMapper.map(addCinemaDto, Cinema.class);

        cinemaRepository.save(mappedCinema);

        return true;
    }
}
