package bg.softuni.regalcinema.service.impl;

import bg.softuni.regalcinema.model.Cinema;
import bg.softuni.regalcinema.model.Movie;
import bg.softuni.regalcinema.model.Program;
import bg.softuni.regalcinema.model.ProgramMovieEntity;
import bg.softuni.regalcinema.model.dtos.exportDtos.MovieInfoDto;
import bg.softuni.regalcinema.model.dtos.exportDtos.ProgramInfoDto;
import bg.softuni.regalcinema.model.dtos.importDtos.AddProgramDto;
import bg.softuni.regalcinema.model.dtos.exportDtos.ProgramMovieInfoDto;
import bg.softuni.regalcinema.repo.CinemaRepository;
import bg.softuni.regalcinema.repo.MovieRepository;
import bg.softuni.regalcinema.repo.ProgramMovieEntityRepository;
import bg.softuni.regalcinema.repo.ProgramRepository;
import bg.softuni.regalcinema.service.ProgramService;
import bg.softuni.regalcinema.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepository programRepository;
    private final MovieServiceImpl movieService;
    private final CinemaRepository cinemaRepository;
    private final ProgramMovieEntityRepository programMovieEntityRepository;
    private final ModelMapper modelMapper;

    public ProgramServiceImpl(ProgramRepository programRepository, MovieServiceImpl movieService, CinemaRepository cinemaRepository, ProgramMovieEntityRepository programMovieEntityRepository, ModelMapper modelMapper) {
        this.programRepository = programRepository;
        this.movieService = movieService;
        this.cinemaRepository = cinemaRepository;
        this.programMovieEntityRepository = programMovieEntityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean add(AddProgramDto programDto) {

        Program mappedProgram = modelMapper.map(programDto, Program.class);

        List<String> movieInfoList = Arrays.stream(programDto.getMoviesInfo().split("\\r\\n")).toList();
        for (String movieInfo : movieInfoList) {
            String[] info = movieInfo.split(" / ");
            String title = info[0];

            if (info[0].contains(" ")) {
                title = URLEncoder.encode(info[0], StandardCharsets.UTF_8).replace("%2B", "+");
            }

            MovieInfoDto movie = movieService.getMovieInfoByTitle(title);
            double ticketPrice = Double.parseDouble(info[1]);
            String starHours = info[2];

            ProgramMovieEntity programMovie =
                    new ProgramMovieEntity(movie.getTitle(), movie.getImageUrl(), movie.getPremiere(), starHours, ticketPrice);

            ProgramMovieEntity savedMovie = programMovieEntityRepository.save(programMovie);

            mappedProgram.addMovie(savedMovie);
        }

        addCinemas(programDto, mappedProgram);

        Optional<Program> byDate = programRepository.findByDate(programDto.getDate());

        if (byDate.isPresent()) {
            for (Cinema cinema : mappedProgram.getCinemas()) {
                boolean containsCinema = byDate.get()
                        .getCinemas()
                        .stream()
                        .anyMatch(cinema1 -> cinema1.getName().equals(cinema.getName()));

                if (containsCinema) {
                    return false;
                }
            }
        }

        // Home Alone / 7.99 / 13:40, 15:00, 16:50, 18:00
        // Harry Potter and the Sorcerers Stone / 10.99 / 15:40, 17:00, 18:50, 21:00
        // Jurassic Park / 16.99 / 14:45, 15:15, 18:30, 20:20
        // 8 Mile / 12.99 / 14:00, 15:15, 17:30, 19:20

        this.programRepository.save(mappedProgram);
        return true;
    }

    @Override
    public List<ProgramMovieInfoDto> getShortInfo(Long cinemaId, String date) {

        Cinema cinema = this.cinemaRepository
                .findById(cinemaId)
                .orElseThrow(() -> new ObjectNotFoundException("Program"));

        Program program = cinema.getPrograms()
                .stream()
                .filter(p -> p.getDate().toString().equals(date))
                .findAny()
                .orElseThrow(() -> new ObjectNotFoundException("Program"));

        List<ProgramMovieInfoDto> resultList = new ArrayList<>();

        program.getMovies().stream().map(movie -> modelMapper.map(movie, ProgramMovieInfoDto.class)).forEach(resultList::add);

        return resultList;

        //        List<String> movieInfoList = Arrays.stream(program.getMoviesInfo().split("\\r\\n")).toList();
//        for (String movieInfo : movieInfoList) {
//            String[] info = movieInfo.split(" / ");
//            String title = info[0];
//
//            if (info[0].contains(" ")) {
//                title = URLEncoder.encode(info[0], StandardCharsets.UTF_8).replace("%2B", "+");
//            }
//
//            MovieInfoDto movie = movieService.getMovieInfoByTitle(title);
//            double ticketPrice = Double.parseDouble(info[1]);
//
//            List<String> hoursList = Arrays.stream(info[2].split(", ")).toList();
//            ProgramMovieInfoDto programMovieInfoDto =
//                    new ProgramMovieInfoDto(movie.getTitle(), movie.getImageUrl(), movie.getPremiere(), hoursList, ticketPrice);
//
//            resultList.add(programMovieInfoDto);
//        }
    }

    @Override
    public List<String> getAllCinemas() {
        return this.cinemaRepository.findAll().stream().map(Cinema::getName).toList();
    }

    @Override
    public List<ProgramInfoDto> findProgramsByCinemaId(Long cinemaId) {

        Cinema cinema = this.cinemaRepository.findById(cinemaId).orElseThrow(() -> new ObjectNotFoundException("Program"));

        return cinema.getPrograms().stream().map(program -> modelMapper.map(program, ProgramInfoDto.class)).toList();
    }

//    public void addMovies(AddProgramDto programDto, Program mappedProgram) {
//        mappedProgram.setMovies(new ArrayList<>());
//        programDto.getMovies().forEach(movie -> {
//            Movie optMovie = movieRepository.findByTitle(movie)..orElseThrow(() -> new ObjectNotFoundException("Movie"));
//            mappedProgram.addMovie(optMovie);
//        });
//    }

    public void addCinemas(AddProgramDto programDto, Program mappedProgram) {

        mappedProgram.setCinemas(new ArrayList<>());
        programDto.getCinemas().forEach(cinema -> {
            Cinema optCinema = cinemaRepository.findByName(cinema).orElseThrow(() -> new ObjectNotFoundException("Cinema"));
            mappedProgram.addCinema(optCinema);
        });
    }
}
