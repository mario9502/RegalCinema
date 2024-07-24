package bg.softuni.regalcinema.web;

import bg.softuni.regalcinema.model.dtos.AddMovieDto;
import bg.softuni.regalcinema.service.impl.MovieServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieServiceImpl movieService;

    public MovieController(MovieServiceImpl movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/add")
    public String doAdd(@RequestBody AddMovieDto movieDto){

        if (!this.movieService.add(movieDto)) {
            return "oops";
        }

        return "hello-world";
    }
}
