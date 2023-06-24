package ru.chernov.urlshortener.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static ru.chernov.urlshortener.consts.rest.Routes.PATH_API_USERS;
import static ru.chernov.urlshortener.consts.rest.Routes.PATH_AUTH;
import static ru.chernov.urlshortener.consts.rest.Routes.PATH_SHORT_LINK;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(requests ->
                        requests
                                .requestMatchers("/", "/*").permitAll()
                                .requestMatchers(HttpMethod.GET, "/v3/api-docs", "/v3/api-docs/**", "/swagger-ui/**").anonymous()
                                .requestMatchers(HttpMethod.POST, PATH_API_USERS).anonymous()
                                .requestMatchers(HttpMethod.GET, PATH_SHORT_LINK).anonymous()
                                .requestMatchers(HttpMethod.POST, PATH_AUTH).anonymous()
                                .anyRequest().authenticated())
                .csrf().disable();

        return httpSecurity.build();
    }

}
