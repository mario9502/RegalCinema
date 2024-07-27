package bg.softuni.regalcinema.web;

import bg.softuni.regalcinema.model.dtos.AddProgramDto;
import bg.softuni.regalcinema.model.dtos.ProgramMovieInfoDto;
import bg.softuni.regalcinema.service.impl.ProgramServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/program")
public class ProgramController {

    private final ProgramServiceImpl programService;

    public ProgramController(ProgramServiceImpl programService) {
        this.programService = programService;
    }

    @PostMapping("/add")
    public String doAdd(@RequestBody AddProgramDto programDto){

        this.programService.add(programDto);

        return "hello-world";
    }

    @GetMapping("/view/{date}")
    public String viewMovieInfo(@PathVariable String date){

        List<ProgramMovieInfoDto> shortInfo = this.programService.getShortInfo(date);

        return "hello-world";
    }


}
