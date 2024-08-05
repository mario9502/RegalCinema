package bg.softuni.regalcinema.init;

import bg.softuni.regalcinema.service.impl.CinemaServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class FirstInit implements CommandLineRunner {

    private final CinemaServiceImpl cinemaService;

    public FirstInit(CinemaServiceImpl cinemaService) {
        this.cinemaService = cinemaService;
    }

    @Override
    public void run(String... args) throws Exception {

        this.cinemaService.init();
    }
}
