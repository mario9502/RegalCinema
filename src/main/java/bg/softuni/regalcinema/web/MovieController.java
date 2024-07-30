package bg.softuni.regalcinema.web;

import bg.softuni.regalcinema.model.dtos.AddMovieDto;
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

    @ModelAttribute("movieData")
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

        return "movie-add";
//        if (!this.movieService.add(movieDto)) {
//            return "oops";
//        }
//
//        return "hello-world";
    }

    @GetMapping("/{id}")
    public void getMovieInfo(@PathVariable Long id) {

        this.movieService.getMovieInfo(id);
    }

    @GetMapping("/all")
    public void getAll(){

        this.movieService.getAll();
    }
}
