package bg.softuni.regalcinema.service.impl;

import bg.softuni.regalcinema.model.Cinema;
import bg.softuni.regalcinema.model.Movie;
import bg.softuni.regalcinema.model.Program;
import bg.softuni.regalcinema.model.dtos.AddProgramDto;
import bg.softuni.regalcinema.model.dtos.ProgramMovieInfoDto;
import bg.softuni.regalcinema.repo.CinemaRepository;
import bg.softuni.regalcinema.repo.MovieRepository;
import bg.softuni.regalcinema.repo.ProgramRepository;
import bg.softuni.regalcinema.service.ProgramService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepository programRepository;
    private final MovieRepository movieRepository;
    private final CinemaRepository cinemaRepository;
    private final ModelMapper modelMapper;

    public ProgramServiceImpl(ProgramRepository programRepository, MovieRepository movieRepository, CinemaRepository cinemaRepository, ModelMapper modelMapper) {
        this.programRepository = programRepository;
        this.movieRepository = movieRepository;
        this.cinemaRepository = cinemaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean add(AddProgramDto programDto) {

        Program mappedProgram = modelMapper.map(programDto, Program.class);
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

        this.programRepository.save(mappedProgram);

        // Home Alone / 7.99 / 13:40, 15:00, 16:50, 18:00
        // Harry Potter / 10.99 / 15:40, 17:00, 18:50, 21:00
        // Dune: Part One / 16.99 / 13:40, 15:00, 16:50, 18:00
        return true;
    }

    @Override
    public List<ProgramMovieInfoDto> getShortInfo(String date) {
        List<ProgramMovieInfoDto> resultList = new ArrayList<>();

        LocalDate parsedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        Program program = programRepository.findByDate(parsedDate).orElse(null);

        List<String> movieInfoList = Arrays.stream(program.getMoviesInfo().split("\\r\\n")).toList();
        for (String movieInfo : movieInfoList) {
            String[] info = movieInfo.split(" / ");

            Movie movie = movieRepository.findByTitle(info[0]).orElse(null);
            double ticketPrice = Double.parseDouble(info[1]);

            List<String> hoursList = Arrays.stream(info[2].split(", ")).toList();
            ProgramMovieInfoDto programMovieInfoDto =
                    new ProgramMovieInfoDto(movie.getTitle(), movie.getImageUrl(), movie.getPremiere(), hoursList, ticketPrice);

            resultList.add(programMovieInfoDto);
        }

        return resultList;
    }

    @Override
    public List<String> getAllCinemas() {
        return this.cinemaRepository.findAll().stream().map(Cinema::getName).toList();
    }

//    public void addMovies(AddProgramDto programDto, Program mappedProgram) {
//        mappedProgram.setMovies(new ArrayList<>());
//        programDto.getMovies().forEach(movie -> {
//            Movie optMovie = movieRepository.findByTitle(movie).orElse(null); //TODO throw and exception instead of null
//            mappedProgram.addMovie(optMovie);
//        });
//    }

    public void addCinemas(AddProgramDto programDto, Program mappedProgram) {

        mappedProgram.setCinemas(new ArrayList<>());
        programDto.getCinemas().forEach(cinema -> {
            Cinema optCinema = cinemaRepository.findByName(cinema).orElse(null);//TODO throw and exception instead of null
            mappedProgram.addCinema(optCinema);
        });
    }
}
