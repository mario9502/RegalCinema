package bg.softuni.regalcinema.web;

import bg.softuni.regalcinema.model.dtos.exportDtos.ShortMovieInfoDto;
import bg.softuni.regalcinema.model.dtos.importDtos.AddMovieDto;
import bg.softuni.regalcinema.model.enums.Audio;
import bg.softuni.regalcinema.model.enums.Genre;
import bg.softuni.regalcinema.service.impl.MovieServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieServiceImpl movieService;

    public MovieController(MovieServiceImpl movieService) {
        this.movieService = movieService;
    }

    @ModelAttribute("addMovieData")
    private AddMovieDto movieDto(){
        return new AddMovieDto();
    }

    @GetMapping("/add")
    public String viewAdd(Model model){

        model.addAttribute("genres", Genre.values());
        model.addAttribute("audios", Audio.values());

        return "movie-add";
    }

    @PostMapping("/add")
    public String doAdd(AddMovieDto movieDto) {

        this.movieService.add(movieDto);

        return "redirect:/movies/add";
//        if (!this.movieService.add(movieDto)) {
//            return "oops";
//        }
//
//        return "hello-world";
    }

    @GetMapping("/{id}")
    public String getMovieInfo(@PathVariable Long id, Model model) {

        model.addAttribute("movieInfo", this.movieService.getMovieInfo(id));

        return "movie-details";
    }

    @ModelAttribute("shortMovieData")
    private ShortMovieInfoDto shortMovieInfoDto(){

        return new ShortMovieInfoDto();
    }

    @GetMapping
    public String getAll(Model model){

        model.addAttribute("allMovies", this.movieService.getAllShortInfo());

        return "movies";
    }
}
