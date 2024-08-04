package bg.softuni.regalcinema.service.impl;

import bg.softuni.regalcinema.model.Cinema;
import bg.softuni.regalcinema.model.dtos.importDtos.AddCinemaDto;
import bg.softuni.regalcinema.repo.CinemaRepository;
import bg.softuni.regalcinema.service.CinemaService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaServiceImpl implements CinemaService {

    private static final List<Cinema> CINEMA_LIST = List.of(
            new Cinema("Regal Cinema", "Hyannis", "+1874521546", "14:00-23:00", "Cape Cod best cinema", "https://static.wikia.nocookie.net/c9dde1fd-8d96-4288-97ea-6dca45a39891/scale-to-width/755"),
            new Cinema("CineGrand", "Sofia", "0878363532", "14:00-23:00", "Luxury cinema", "https://static.wikia.nocookie.net/c9dde1fd-8d96-4288-97ea-6dca45a39891/scale-to-width/755"),
            new Cinema("Cinema City", "Ruse", "0878363533", "14:00-23:00", "Top gadno kino", "https://static.wikia.nocookie.net/c9dde1fd-8d96-4288-97ea-6dca45a39891/scale-to-width/755"),
            new Cinema("Kino Arena", "Varna", "0878363588", "14:00-23:00", "IMAX top max", "https://static.wikia.nocookie.net/c9dde1fd-8d96-4288-97ea-6dca45a39891/scale-to-width/755"),
            new Cinema("Kinopolis", "Pleven", "0878363193", "14:00-23:00", "Cheap cinema", "https://static.wikia.nocookie.net/c9dde1fd-8d96-4288-97ea-6dca45a39891/scale-to-width/755")
    );

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

    @Override
    public void init() {

        if (this.cinemaRepository.count() < 1) {
            this.cinemaRepository.saveAll(CINEMA_LIST);
        }
    }
}
