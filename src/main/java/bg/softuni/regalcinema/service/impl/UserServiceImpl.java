package bg.softuni.regalcinema.service.impl;

import bg.softuni.regalcinema.model.User;
import bg.softuni.regalcinema.model.dtos.importDtos.UserRegisterDto;
import bg.softuni.regalcinema.model.enums.UserRole;
import bg.softuni.regalcinema.repo.UserRepository;
import bg.softuni.regalcinema.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public boolean register(UserRegisterDto registerDto) {

        if (userRepository.existsByUsernameOrEmail(registerDto.getUsername(), registerDto.getEmail())) {
            return false;
        }

        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            return false;
        }

        User mappedUser = modelMapper.map(registerDto, User.class);

        if (userRepository.count() < 1) {
            mappedUser.setRole(UserRole.ADMIN);
        } else {
            mappedUser.setRole(UserRole.USER);
        }

        userRepository.save(mappedUser);

        return true;
    }
}
