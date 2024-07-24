package bg.softuni.regalcinema.web;

import bg.softuni.regalcinema.model.dtos.AddCinemaDto;
import bg.softuni.regalcinema.service.impl.CinemaServiceImpl;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cinemas")
public class CinemaController {

    private final CinemaServiceImpl cinemaService;

    public CinemaController(CinemaServiceImpl cinemaService) {
        this.cinemaService = cinemaService;
    }

    @PostMapping("/add")
    public String doAdd(@RequestBody @Valid AddCinemaDto addCinemaDto){

        if (!this.cinemaService.add(addCinemaDto)) {
            return "oops";
        }

        return "hello-world";
    }

}
