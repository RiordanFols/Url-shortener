package ru.chernov.urlshortener.service.setting;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.chernov.urlshortener.dto.setting.SettingKey;
import ru.chernov.urlshortener.entity.Setting;
import ru.chernov.urlshortener.exception.setting.SettingNotFoundException;
import ru.chernov.urlshortener.exception.setting.SettingWrongTypeException;
import ru.chernov.urlshortener.repository.setting.SettingRepository;


@Service
public class SettingService {
    private static final Logger logger = LogManager.getLogger(SettingService.class);

    private final SettingRepository settingRepository;


    public SettingService(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
    }


    public Setting findById(String name) {
        return settingRepository.findById(name).orElseThrow(() -> {
            logger.error("Setting [{}} not found.", name);
            return new SettingNotFoundException();
        });
    }


    public <T> T get(SettingKey<T> settingKey) {
        Setting setting = findById(settingKey.name());
        if (setting.getType() != settingKey.settingType()) {
            logger.error("Wrong setting type for [{}].", settingKey.name());
            throw new SettingWrongTypeException();
        }
        return settingKey.parser().apply(setting);
    }

}
