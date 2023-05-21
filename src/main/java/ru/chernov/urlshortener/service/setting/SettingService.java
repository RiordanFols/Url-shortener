package ru.chernov.urlshortener.service.setting;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.chernov.urlshortener.dto.setting.SettingKey;
import ru.chernov.urlshortener.entity.Setting;
import ru.chernov.urlshortener.exception.setting.SettingNotFoundException;
import ru.chernov.urlshortener.exception.setting.SettingWrongTypeException;
import ru.chernov.urlshortener.repository.setting.SettingRepository;

import java.util.Optional;


@Service
public class SettingService {
    private static final Logger logger = LogManager.getLogger(SettingService.class);

    private final SettingRedisService settingRedisService;
    private final SettingRepository settingRepository;


    public SettingService(SettingRedisService settingRedisService,
                          SettingRepository settingRepository) {
        this.settingRedisService = settingRedisService;
        this.settingRepository = settingRepository;
    }


    public Setting findById(String name) {
        return settingRepository.findById(name).orElseThrow(() -> {
            logger.error("Setting [{}} not found.", name);
            return new SettingNotFoundException();
        });
    }


    public <T> T get(SettingKey<T> settingKey) {
        Optional<String> cache = settingRedisService.read(settingKey);
        if (cache.isPresent()) {
            return settingKey.parser().apply(cache.get());
        } else {
            Setting setting = findById(settingKey.name());
            if (setting.getType() != settingKey.settingType()) {
                logger.error("Wrong setting type for [{}].", settingKey.name());
                throw new SettingWrongTypeException();
            }
            String value = setting.getValue();
            settingRedisService.write(settingKey, value);
            return settingKey.parser().apply(value);
        }
    }

}
