package bg.softuni.regalcinema.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public DefaultSecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .authorizeHttpRequests(
                        authorizeRequest ->
                                authorizeRequest
                                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                        .requestMatchers("/", "/users/register", "/users/login",
                                                "/movies", "/movies/{id}", "/cinemas").permitAll()
                                        .anyRequest().authenticated()
                )
                .formLogin(
                        formLogin ->
                                formLogin
                                        .loginPage("/users/login")
                                        .usernameParameter("username")
                                        .passwordParameter("password")
                                        .defaultSuccessUrl("/", true)
                                        .failureForwardUrl("/users/login")
                )
                .logout(
                        logout ->
                                logout
                                        .logoutUrl("/users/logout")
                                        .logoutSuccessUrl("/")
                                        .invalidateHttpSession(true)
                )
                .build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
