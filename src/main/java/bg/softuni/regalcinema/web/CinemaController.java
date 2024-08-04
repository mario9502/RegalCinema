package bg.softuni.regalcinema.web;

import bg.softuni.regalcinema.model.dtos.exportDtos.CinemaInfoDto;
import bg.softuni.regalcinema.model.dtos.exportDtos.ShortCinemaInfoDto;
import bg.softuni.regalcinema.model.dtos.importDtos.AddCinemaDto;
import bg.softuni.regalcinema.service.impl.CinemaServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String doAdd(AddCinemaDto addCinemaDto){

        if (!this.cinemaService.add(addCinemaDto)) {
            return "oops";
        }

        return "redirect:/cinemas/add";
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
        //TODO do the program buttons

        return "cinema-details";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){

        this.cinemaService.delete(id);

        return "redirect:/cinemas";
    }

}
