package bg.softuni.regalcinema.service.impl;

import bg.softuni.regalcinema.model.UserEntity;
import bg.softuni.regalcinema.repo.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " does not exist!"));

        return User
                .withUsername(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities(List.of(new SimpleGrantedAuthority("ROLE_" + userEntity.getRole().name())))
                .disabled(false)
                .build();
    }
}
