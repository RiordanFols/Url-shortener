package ru.chernov.urlshortener.entity;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ru.chernov.urlshortener.converter.SettingTypeConverter;
import ru.chernov.urlshortener.enums.setting.SettingType;


@Data
@Entity
@Table(name = "setting")
public class Setting {

    @Id
    @Length(max = 255)
    private String name;

    @Convert(converter = SettingTypeConverter.class)
    @NotNull
    private SettingType type;

    @Length(max = 255)
    @NotNull
    private String value;

}
