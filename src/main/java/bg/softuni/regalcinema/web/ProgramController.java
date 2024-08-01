package bg.softuni.regalcinema.web;

import bg.softuni.regalcinema.model.dtos.AddProgramDto;
import bg.softuni.regalcinema.model.dtos.ProgramMovieInfoDto;
import bg.softuni.regalcinema.service.impl.ProgramServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/programs")
public class ProgramController {

    private final ProgramServiceImpl programService;

    public ProgramController(ProgramServiceImpl programService) {
        this.programService = programService;
    }

    @ModelAttribute("programData")
    private AddProgramDto programDto(){
        return new AddProgramDto();
    }

    @GetMapping("/add")
    public String viewAdd(Model model){

        model.addAttribute("cinemas" ,this.programService.getAllCinemas());

        return "program-add";
    }

    @PostMapping("/add")
    @Transactional
    public String doAdd(AddProgramDto programDto){

        if (!this.programService.add(programDto)) {
            return "oops";
        }

        return "redirect:/programs/add";
    }

    @GetMapping("/view/{date}")
    public String viewMovieInfo(@PathVariable String date){

        List<ProgramMovieInfoDto> shortInfo = this.programService.getShortInfo(date);

        return "hello-world";
    }


}
