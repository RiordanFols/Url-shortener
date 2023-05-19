package ru.chernov.urlshortener.config.security;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.chernov.urlshortener.service.user.UserService;

import static ru.chernov.urlshortener.consts.rest.Routes.PATH_API_USERS;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final UserService userService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;


    public WebSecurityConfig(UserService userService,
                             AuthenticationManagerBuilder authenticationManagerBuilder,
                             PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.passwordEncoder = passwordEncoder;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(requests ->
                        requests
                                .requestMatchers("/", "/*").permitAll()
                                .requestMatchers(HttpMethod.POST, PATH_API_USERS).anonymous()
                                .anyRequest().authenticated())
                .csrf().disable();

        return httpSecurity.build();
    }


    @PostConstruct
    public void authenticationManagerBuilder() throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
    }

}
