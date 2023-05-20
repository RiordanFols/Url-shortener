package ru.chernov.urlshortener.service.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.chernov.urlshortener.dto.user.UserRegisterRequest;
import ru.chernov.urlshortener.entity.user.User;
import ru.chernov.urlshortener.exception.link.LinkNotFoundException;
import ru.chernov.urlshortener.exception.user.UserNotFoundException;
import ru.chernov.urlshortener.repository.user.UserRepository;

import java.util.UUID;


@Service
public class UserService implements UserDetailsService {
    private static final Logger logger = LogManager.getLogger(UserService.class);

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    public UserService(PasswordEncoder passwordEncoder,
                       UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    @Override
    public User loadUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> {
            logger.error("User username=[{}] not found.", username);
            throw new LinkNotFoundException();
        });
    }


    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            logger.error("User [{}] not found.", id);
            throw new UserNotFoundException();
        });
    }


    public void register(UserRegisterRequest registerRequest) {
        var user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setToken(UUID.randomUUID());
        user.setActive(true);
        userRepository.save(user);
    }

}
