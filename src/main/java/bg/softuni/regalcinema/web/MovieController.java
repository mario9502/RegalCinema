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

        //{
        //    "title": "Harry Potter",
        //    "directorName": "Chris Columbus",
        //    "actors": "Daniel Radcliffe, Emma Watson, Rupert Green, Tom Felton...",
        //    "description": "An orphaned boy enrolls in a school of wizardry, where he learns the truth about himself, his family and the terrible evil that haunts the magical world.",
        //    "lengthInMinutes": "152",
        //    "premiere": "15-02-2002",
        //    "videoUrl": "https://www.youtube.com/watch?v=VyHV0BRtdxo",
        //    "audio": "ENGLISH",
        //    "genre": "FANTASY"
        //}

        return "hello-world";
    }
}
