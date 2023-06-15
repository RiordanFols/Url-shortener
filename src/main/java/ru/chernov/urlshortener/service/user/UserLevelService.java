package ru.chernov.urlshortener.service.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.chernov.urlshortener.entity.user.UserLevel;
import ru.chernov.urlshortener.exception.user.UserLevelNotFoundException;
import ru.chernov.urlshortener.repository.user.UserLevelRepository;


@Service
public class UserLevelService {
    private static final Logger logger = LogManager.getLogger(UserLevelService.class);

    private final UserLevelRepository userLevelRepository;


    public UserLevelService(UserLevelRepository userLevelRepository) {
        this.userLevelRepository = userLevelRepository;
    }


    public UserLevel findByName(String name) {
        return userLevelRepository.findById(name).orElseThrow(() -> {
            logger.error("User level [{}] not found.", name);
            return new UserLevelNotFoundException();
        });
    }

}
