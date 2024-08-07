package bg.softuni.regalcinema.web;

import bg.softuni.regalcinema.model.dtos.exportDtos.ShortMovieInfoDto;
import bg.softuni.regalcinema.model.dtos.importDtos.AddMovieDto;
import bg.softuni.regalcinema.model.enums.Audio;
import bg.softuni.regalcinema.model.enums.Genre;
import bg.softuni.regalcinema.service.impl.MovieServiceImpl;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String doAdd(@Valid AddMovieDto movieDto, BindingResult bindingResult, RedirectAttributes rAtt) {

        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("addMovieData", movieDto);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.addMovieData", bindingResult);

            return "redirect:/movies/add";
        }

        this.movieService.add(movieDto);

        return "redirect:/movies";
//        if (!this.movieService.add(movieDto)) {
//            return "oops";
//        }
//
//        return "hello-world";
    }

    @GetMapping("/{id}")
    public String getMovieInfoById(@PathVariable Long id, Model model) {

        model.addAttribute("movieInfo", this.movieService.getMovieInfoById(id));


        return "movie-details";
    }

    @GetMapping("/title={title}")
    public String getMovieInfoByTitle(@PathVariable String title, Model model) {

        model.addAttribute("movieInfo", this.movieService.getMovieInfoByTitle(title));

        return "movie-details";
    }

    @ModelAttribute("shortMovieData")
    private ShortMovieInfoDto shortMovieInfoDto(){

        return new ShortMovieInfoDto();
    }

    @GetMapping
    public String viewAll(Model model){

        model.addAttribute("allMovies", this.movieService.getAllShortInfo());

        return "movies";
    }

    @DeleteMapping("/{id}")
    @Transactional
    public String delete(@PathVariable Long id){

        this.movieService.deleteById(id);

        return "redirect:/movies";
    }
}
