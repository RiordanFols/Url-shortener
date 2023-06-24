package ru.chernov.urlshortener.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.chernov.urlshortener.config.security.jwt.JwtAuthFilter;

import static ru.chernov.urlshortener.consts.rest.Routes.PATH_API_USERS;
import static ru.chernov.urlshortener.consts.rest.Routes.PATH_AUTH;
import static ru.chernov.urlshortener.consts.rest.Routes.PATH_SHORT_LINK;


@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
public class WebSecurityConfig {
    private static final String PATH_DOCS = "/v3/api-docs";
    private static final String PATH_DOCS_EXTRA = "/v3/api-docs/**";
    private static final String PATH_SWAGGER_UI = "/swagger-ui/**";

    private final JwtAuthFilter jwtAuthFilter;


    public WebSecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }


//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers("")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests()
                    .requestMatchers(HttpMethod.GET, PATH_DOCS, PATH_DOCS_EXTRA, PATH_SWAGGER_UI).permitAll()
                    .requestMatchers(HttpMethod.POST, PATH_API_USERS).permitAll()
                    .requestMatchers(HttpMethod.GET, PATH_SHORT_LINK).permitAll()
                    .requestMatchers(HttpMethod.POST, PATH_AUTH).permitAll()
                .anyRequest()
                    .authenticated()
                .and()
                    .exceptionHandling()
                    .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .and()
                    .csrf().disable()
                    .cors().disable();

        return httpSecurity.build();
    }

}
