package ru.chernov.urlshortener.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.chernov.urlshortener.dto.setting.SettingKey;
import ru.chernov.urlshortener.service.setting.SettingService;

import java.util.Locale;


@Service
public class LocalizationService {
    private final SettingService settingService;
    private final MessageSource messageSource;


    public LocalizationService(SettingService settingService,
                               MessageSource messageSource) {
        this.settingService = settingService;
        this.messageSource = messageSource;
    }


    // TODO: вызывать в ExceptionHandler
    public String localize(String code) {
        // TODO: cache in redis
        Locale locale = new Locale(settingService.get(SettingKey.LANG));
        return messageSource.getMessage(code, new Object[]{}, locale);
    }

}
