package bg.softuni.regalcinema.service.impl;

import bg.softuni.regalcinema.model.Cinema;
import bg.softuni.regalcinema.model.dtos.importDtos.AddCinemaDto;
import bg.softuni.regalcinema.repo.CinemaRepository;
import bg.softuni.regalcinema.service.exception.IllegalArgException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CinemaServiceImplTest {

    private CinemaServiceImpl toTest;

    @Captor
    private ArgumentCaptor<Cinema> cinemaCaptor;

    @Mock
    private CinemaRepository mockedCinemaRepository;

    @BeforeEach
    void setUp(){
        this.toTest = new CinemaServiceImpl(mockedCinemaRepository, new ModelMapper());
    }

    @Test
    void testAddCinema_ShouldThrow(){
        AddCinemaDto cinemaDto = getAddCinemaDto();

        when(mockedCinemaRepository.existsByName(cinemaDto.getName())).thenReturn(true);

        Assertions.assertThrows(IllegalArgException.class, () -> toTest.add(cinemaDto));
    }

    private static AddCinemaDto getAddCinemaDto() {
        return new AddCinemaDto(
                "Test Cinema",
                "Test Location",
                "Test Description",
                "Test Number",
                "Test Work Time",
                "www.sometesturl.com/test/123123123"
        );
    }
}
