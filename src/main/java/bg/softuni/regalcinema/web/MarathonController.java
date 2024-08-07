package bg.softuni.regalcinema.web;

import bg.softuni.regalcinema.model.dtos.importDtos.AddMarathonDto;
import bg.softuni.regalcinema.service.impl.MarathonServiceImpl;
import bg.softuni.regalcinema.service.impl.MovieServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/marathons")
public class MarathonController {

    private final MarathonServiceImpl marathonService;
    private final MovieServiceImpl movieService;

    public MarathonController(MarathonServiceImpl marathonService, MovieServiceImpl movieService) {
        this.marathonService = marathonService;
        this.movieService = movieService;
    }

    @ModelAttribute("marathonData")
    private AddMarathonDto marathonDto(){
        return new AddMarathonDto();
    }

    @GetMapping("/add")
    public String viewAdd(Model model){

        model.addAttribute("allMovies", this.movieService.getAllShortInfo());

        return "marathon-add";
    }


    @PostMapping("/add")
    public String doAdd(AddMarathonDto marathonDto){

        this.marathonService.add(marathonDto);

        return "hello-world";
    }
}
