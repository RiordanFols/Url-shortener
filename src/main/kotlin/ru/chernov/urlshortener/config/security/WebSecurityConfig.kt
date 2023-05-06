package ru.chernov.urlshortener.config.security

import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import ru.chernov.urlshortener.service.UserService


@Configuration
@EnableWebSecurity
class WebSecurityConfig(
        val userService: UserService,
        val authenticationManagerBuilder: AuthenticationManagerBuilder,
        val passwordEncoder: PasswordEncoder
) {

    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity
                .authorizeHttpRequests { requests ->
                    requests
                            .requestMatchers("/", "/*").permitAll()
                            .anyRequest().authenticated()
                }
                .csrf().disable()

        return httpSecurity.build()
    }


    @PostConstruct
    fun authenticationManagerBuilder() {
        authenticationManagerBuilder
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder)
    }

}