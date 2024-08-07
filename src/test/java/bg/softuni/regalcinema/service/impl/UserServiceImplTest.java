package bg.softuni.regalcinema.service.impl;

import bg.softuni.regalcinema.model.UserEntity;
import bg.softuni.regalcinema.model.dtos.importDtos.UserRegisterDto;
import bg.softuni.regalcinema.repo.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    private UserServiceImpl toTest;

    @Captor
    private ArgumentCaptor<UserEntity> userEntityCaptor;

    @Mock
    private UserRepository mockedUserRepository;

    @Mock
    private PasswordEncoder mockedPasswordEncoder;

    @BeforeEach
    void setUp(){
        toTest = new UserServiceImpl(new ModelMapper(), mockedUserRepository, mockedPasswordEncoder);
    }

    @Test
    void testUserRegister(){

        UserRegisterDto userRegisterDto = new UserRegisterDto(
                "test_user",
                "testpassword",
                "testpassword",
                "test@email",
                "Test",
                "User"
        );

        when(mockedPasswordEncoder.encode(userRegisterDto.getPassword()))
                .thenReturn(userRegisterDto.getPassword() + "ENCODED");
        when(mockedUserRepository.count()).thenReturn(1L);

        toTest.register(userRegisterDto);

        verify(mockedUserRepository).save(userEntityCaptor.capture());

        UserEntity actualSavedUser = userEntityCaptor.getValue();

        Assertions.assertEquals(userRegisterDto.getUsername(), actualSavedUser.getUsername());
        Assertions.assertEquals(userRegisterDto.getPassword() + "ENCODED", actualSavedUser.getPassword());
        Assertions.assertEquals(userRegisterDto.getEmail(), actualSavedUser.getEmail());
        Assertions.assertEquals(userRegisterDto.getFirstName(), actualSavedUser.getFirstName());
        Assertions.assertEquals("USER", actualSavedUser.getRole().name());
        Assertions.assertEquals(userRegisterDto.getLastName(), actualSavedUser.getLastName());
    }
}
