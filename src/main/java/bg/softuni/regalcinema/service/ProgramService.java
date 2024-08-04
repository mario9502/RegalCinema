package bg.softuni.regalcinema.service;

import bg.softuni.regalcinema.model.Program;
import bg.softuni.regalcinema.model.dtos.exportDtos.ProgramInfoDto;
import bg.softuni.regalcinema.model.dtos.importDtos.AddProgramDto;
import bg.softuni.regalcinema.model.dtos.exportDtos.ProgramMovieInfoDto;

import java.util.List;

public interface ProgramService {
    boolean add(AddProgramDto programDto);

    List<ProgramMovieInfoDto> getShortInfo(Long cinemaId, String date);

    List<String> getAllCinemas();

    List<ProgramInfoDto> findProgramsByCinemaId(Long cinemaId);
}
