package bg.softuni.regalcinema.web;

import bg.softuni.regalcinema.model.dtos.importDtos.UserRegisterDto;
import bg.softuni.regalcinema.repo.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIT {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        this.userRepository.deleteAll();
    }

    @Test
    void testUserRegister() throws Exception {

        UserRegisterDto userRegisterDto = createRegisterDto();

        long before = userRepository.count();

        mockMvc.perform(post("/users/register").with(csrf()).
                param("username", userRegisterDto.getUsername())
                .param("password", userRegisterDto.getPassword())
                .param("confirmPassword", userRegisterDto.getConfirmPassword())
                .param("email", userRegisterDto.getEmail())
                .param("firstName", userRegisterDto.getFirstName())
                .param("lastName", userRegisterDto.getLastName())
        ).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/login"))
        ;

        assertEquals(before + 1, userRepository.count());
    }

    private static UserRegisterDto createRegisterDto() {
        return new UserRegisterDto(
                "test_user",
                "testpassword",
                "testpassword",
                "test@email",
                "Test",
                "User"
        );
    }
}
