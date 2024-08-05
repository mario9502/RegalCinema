package bg.softuni.regalcinema.service.impl;

import bg.softuni.regalcinema.model.UserEntity;
import bg.softuni.regalcinema.model.dtos.importDtos.UserRegisterDto;
import bg.softuni.regalcinema.model.enums.UserRole;
import bg.softuni.regalcinema.repo.UserRepository;
import bg.softuni.regalcinema.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean register(UserRegisterDto registerDto) {

        if (userRepository.existsByUsernameOrEmail(registerDto.getUsername(), registerDto.getEmail())) {
            return false;
        }

        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            return false;
        }

        UserEntity mappedUserEntity = modelMapper.map(registerDto, UserEntity.class);
        mappedUserEntity.setPassword(passwordEncoder.encode(mappedUserEntity.getPassword()));

        if (userRepository.count() < 1) {
            mappedUserEntity.setRole(UserRole.ADMIN);
        } else {
            mappedUserEntity.setRole(UserRole.USER);
        }

        userRepository.save(mappedUserEntity);

        return true;
    }
}
