package bg.softuni.regalcinema.service;

import bg.softuni.regalcinema.model.dtos.AddProgramDto;
import bg.softuni.regalcinema.model.dtos.ProgramMovieInfoDto;

import java.util.List;

public interface ProgramService {
    boolean add(AddProgramDto programDto);

    List<ProgramMovieInfoDto> getShortInfo(String date);

    List<String> getAllCinemas();
}
