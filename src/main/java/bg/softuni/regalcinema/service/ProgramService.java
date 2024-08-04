package bg.softuni.regalcinema.service;

import bg.softuni.regalcinema.model.dtos.importDtos.AddProgramDto;
import bg.softuni.regalcinema.model.dtos.exportDtos.ProgramMovieInfoDto;

import java.util.List;

public interface ProgramService {
    boolean add(AddProgramDto programDto);

    List<ProgramMovieInfoDto> getShortInfo(String date);

    List<String> getAllCinemas();
}
