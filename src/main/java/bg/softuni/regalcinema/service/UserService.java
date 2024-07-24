package bg.softuni.regalcinema.service;

import bg.softuni.regalcinema.model.dtos.UserRegisterDto;

public interface UserService {
    boolean register(UserRegisterDto registerDto);
}
