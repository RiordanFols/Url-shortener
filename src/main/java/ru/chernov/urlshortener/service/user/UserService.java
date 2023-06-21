package ru.chernov.urlshortener.service.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.chernov.urlshortener.dto.user.UserRegisterRequest;
import ru.chernov.urlshortener.entity.user.User;
import ru.chernov.urlshortener.enums.user.UserLevelName;
import ru.chernov.urlshortener.enums.user.UserStatus;
import ru.chernov.urlshortener.exception.link.LinkNotFoundException;
import ru.chernov.urlshortener.exception.user.UserNotFoundException;
import ru.chernov.urlshortener.exception.user.UserStatusException;
import ru.chernov.urlshortener.repository.user.UserRepository;

import java.util.Set;

import static ru.chernov.urlshortener.utils.TimeUtil.utcNow;


@Service
public class UserService implements UserDetailsService {
    private static final Logger logger = LogManager.getLogger(UserService.class);

    private final UserLevelService userLevelService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    public UserService(UserLevelService userLevelService,
                       PasswordEncoder passwordEncoder,
                       UserRepository userRepository) {
        this.userLevelService = userLevelService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    public User getCurrent() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return loadUserByUsername(authentication.getName());
    }


    @Override
    public User loadUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> {
            logger.error("User username=[{}] not found.", username);
            return new LinkNotFoundException();
        });
    }


    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            logger.error("User [{}] not found.", id);
            throw new UserNotFoundException();
        });
    }


    public void validate(User user, Set<UserStatus> allowedStatuses) {
        UserStatus status = user.getStatus();
        if (!allowedStatuses.contains(status)) {
            logger.error("User [{}] has status [{}].", user.getId(), status);
            throw new UserStatusException(status);
        }
    }


    public User register(UserRegisterRequest registerRequest) {
        var user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setStatus(UserStatus.ACTIVE);
        user.setRegisteredAt(utcNow());
        user.setLevel(userLevelService.findByName(UserLevelName.NONE.getDbValue()));
        return userRepository.save(user);
    }


    public void updateLevel(Long userId, UserLevelName userLevelName) {
        var user = findById(userId);
        validate(user, UserStatus.USER_WORKS_STATUSES);
        user.setLevel(userLevelService.findByName(userLevelName.getDbValue()));
        userRepository.save(user);
    }

}
