package bg.softuni.regalcinema.web;

import bg.softuni.regalcinema.model.dtos.exportDtos.ProgramInfoDto;
import bg.softuni.regalcinema.model.dtos.exportDtos.ShortCinemaInfoDto;
import bg.softuni.regalcinema.model.dtos.importDtos.AddProgramDto;
import bg.softuni.regalcinema.model.dtos.exportDtos.ProgramMovieInfoDto;
import bg.softuni.regalcinema.service.exception.IllegalArgException;
import bg.softuni.regalcinema.service.impl.CinemaServiceImpl;
import bg.softuni.regalcinema.service.impl.ProgramServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/programs")
public class ProgramController {

    private final ProgramServiceImpl programService;
    private final CinemaServiceImpl cinemaService;

    public ProgramController(ProgramServiceImpl programService, CinemaServiceImpl cinemaService) {
        this.programService = programService;
        this.cinemaService = cinemaService;
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
    public String doAdd(@Valid AddProgramDto programDto, BindingResult bindingResult, RedirectAttributes rAtt){

        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("programData", programDto);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.programData", bindingResult);

            return "redirect:/programs/add";
        }

        if (!this.programService.add(programDto)) {
            throw new IllegalArgException("Program");
        }

        return "redirect:/programs";
    }

    @GetMapping("/{cinemaId}/{date}")
    @Transactional
    public String viewMovieInfo(@PathVariable("cinemaId") Long cinemaId, @PathVariable("date") String date, Model model){

        List<ProgramInfoDto> programsByCinemaId = this.programService.findProgramsByCinemaId(cinemaId);
        List<ProgramMovieInfoDto> shortInfo = this.programService.getShortInfo(cinemaId, date);

        model.addAttribute("allPrograms", programsByCinemaId);
        model.addAttribute("programsInfoList", shortInfo);

        return "programs";
    }

    @GetMapping
    public String chooseCinema(Model model){

        List<ShortCinemaInfoDto> all = this.cinemaService.getAll();
        model.addAttribute("cinemaList", all);

        return "choose-cinema";
    }

    @GetMapping("/{cinemaId}")
    @Transactional
    public String viewProgram(@PathVariable Long cinemaId, Model model) {

        List<ProgramInfoDto> programsByCinemaId = this.programService.findProgramsByCinemaId(cinemaId);

        model.addAttribute("allPrograms", programsByCinemaId);
        model.addAttribute("cinemaId", cinemaId);

        return "select-date";
    }
}
