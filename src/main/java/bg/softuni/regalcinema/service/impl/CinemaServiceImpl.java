package bg.softuni.regalcinema.service.impl;

import bg.softuni.regalcinema.model.Cinema;
import bg.softuni.regalcinema.model.dtos.exportDtos.CinemaInfoDto;
import bg.softuni.regalcinema.model.dtos.exportDtos.ShortCinemaInfoDto;
import bg.softuni.regalcinema.model.dtos.importDtos.AddCinemaDto;
import bg.softuni.regalcinema.repo.CinemaRepository;
import bg.softuni.regalcinema.service.CinemaService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaServiceImpl implements CinemaService {

    private static final List<Cinema> CINEMA_LIST = List.of(
            new Cinema("Regal Cinema", "Hyannis", "+1874521546", "14:00-23:00", "Cape Cod best cinema", "https://photos.cinematreasures.org/production/photos/180880/1473719218/large.jpg?1473719218"),
            new Cinema("CineGrand", "Sofia", "0878363532", "14:00-23:00", "Luxury cinema", "https://hicomm.bg/uploads/articles/201504/40908/IMG_9035.jpg"),
            new Cinema("Cinema City", "Ruse", "0878363533", "14:00-23:00", "Top gadno kino", "https://rousse.info/wp-content/uploads/2017/02/cinema-city-mall-rousse.jpg"),
            new Cinema("Kino Arena", "Varna", "0878363588", "14:00-23:00", "IMAX top max", "https://www.grandmall-varna.com/media/pictures/crops/600adc9f019b5/30_16.jpeg"),
            new Cinema("Kinopolis", "Pleven", "0878363193", "14:00-23:00", "Cheap cinema", "https://p2.novo5.com/k/i/kinopolis-pleven-1931-1140x0.jpg")
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

    @Override
    public List<ShortCinemaInfoDto> getAll() {

        return this.cinemaRepository
                .findAll()
                .stream()
                .map(cinema -> modelMapper.map(cinema, ShortCinemaInfoDto.class))
                .toList();
    }

    @Override
    public CinemaInfoDto findById(Long id) {

        Cinema cinema = this.cinemaRepository.findById(id).orElse(null); //TODO throw exception instead of null

        return modelMapper.map(cinema, CinemaInfoDto.class);
    }

    @Override
    public void delete(Long id) {

        this.cinemaRepository.deleteById(id);
    }
}
