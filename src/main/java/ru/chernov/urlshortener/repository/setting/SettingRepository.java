package ru.chernov.urlshortener.repository.setting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chernov.urlshortener.entity.Setting;


@Repository
public interface SettingRepository extends JpaRepository<Setting, String> {
}
