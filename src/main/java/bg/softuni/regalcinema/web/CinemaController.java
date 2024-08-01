package bg.softuni.regalcinema.web;

import bg.softuni.regalcinema.model.dtos.AddCinemaDto;
import bg.softuni.regalcinema.service.impl.CinemaServiceImpl;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public String doAdd(AddCinemaDto addCinemaDto){

        if (!this.cinemaService.add(addCinemaDto)) {
            return "oops";
        }

        return "redirect:/cinemas/add";
    }

}
