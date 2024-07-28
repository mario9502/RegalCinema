package bg.softuni.regalcinema.web;

import bg.softuni.regalcinema.model.dtos.AddMovieDto;
import bg.softuni.regalcinema.service.impl.MovieServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieServiceImpl movieService;

    public MovieController(MovieServiceImpl movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/add")
    public void doAdd(@RequestBody AddMovieDto movieDto) {

        this.movieService.add(movieDto);

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
