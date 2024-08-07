package bg.softuni.regalcinema.web;

import bg.softuni.regalcinema.model.Cinema;
import bg.softuni.regalcinema.repo.CinemaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CinemaControllerIT {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void addCinema() throws Exception {

        Cinema testCinema = createTestCinema();

        long before = cinemaRepository.count();

        mockMvc.perform(post("/cinemas/add")
                .with(csrf())
                .param("name", testCinema.getName())
                .param("location", testCinema.getLocation())
                .param("description", testCinema.getDescription())
                .param("phoneNumber", testCinema.getPhoneNumber())
                .param("imageUrl", testCinema.getImageUrl())
                .param("workingTime", testCinema.getWorkingTime())
                ).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cinemas"));

        assertEquals(cinemaRepository.count(), before + 1);

    }

    @Test
    void getCinemaInfo() throws Exception {

        Cinema testCinema = createTestCinema();
        Cinema actualCinema = cinemaRepository.save(testCinema);


        mockMvc.perform(get("/cinemas/{id}", actualCinema.getId())
                .contentType(MediaType.TEXT_HTML)
                ).andExpect(status().is2xxSuccessful())
                .andExpect(view().name("cinema-details"))
                .andExpect(model().attributeExists("cinemaInfo"));
    }

    private static Cinema createTestCinema() {
        return new Cinema(
                "Test Cinema",
                "Test Location",
                "0878363535",
                "14:00-21:00",
                "Test Work Time Time",
                "https://www.shutterstock.com/image-photo/very-random-pose-asian-men-260nw-2423213779.jpg"
        );
    }
}
