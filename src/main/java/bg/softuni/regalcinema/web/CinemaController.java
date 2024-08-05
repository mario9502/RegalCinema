package bg.softuni.regalcinema.web;

import bg.softuni.regalcinema.model.dtos.exportDtos.CinemaInfoDto;
import bg.softuni.regalcinema.model.dtos.exportDtos.ShortCinemaInfoDto;
import bg.softuni.regalcinema.model.dtos.importDtos.AddCinemaDto;
import bg.softuni.regalcinema.service.exception.IllegalArgException;
import bg.softuni.regalcinema.service.impl.CinemaServiceImpl;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/cinemas")
public class CinemaController {

    private final CinemaServiceImpl cinemaService;

    public CinemaController(CinemaServiceImpl cinemaService) {
        this.cinemaService = cinemaService;
    }

    @ModelAttribute("cinemaData")
    private AddCinemaDto cinemaDto() {
        return new AddCinemaDto();
    }

    @GetMapping("/add")
    public String viewAdd(){

        return "cinema-add";
    }

    @PostMapping("/add")
    public String doAdd(@Valid AddCinemaDto addCinemaDto, BindingResult bindingResult, RedirectAttributes rAtt){

        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("cinemaData", addCinemaDto);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.cinemaData", bindingResult);

            return "redirect:/cinemas/add";
        }

        this.cinemaService.add(addCinemaDto);

        return "redirect:/cinemas";
    }

    @GetMapping
    public String viewAll(Model model){

        List<ShortCinemaInfoDto> cinemaInfoDtos = this.cinemaService.getAll();
        model.addAttribute("cinemas", cinemaInfoDtos);

        return "cinemas";
    }

    @GetMapping("/{id}")
    @Transactional
    public String getCinemaInfo(@PathVariable Long id, Model model){

        CinemaInfoDto cinemaInfo = this.cinemaService.findById(id);
        model.addAttribute("cinemaInfo", cinemaInfo);

        return "cinema-details";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){

        this.cinemaService.delete(id);

        return "redirect:/cinemas";
    }

}
