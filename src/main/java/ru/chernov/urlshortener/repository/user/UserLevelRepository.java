package ru.chernov.urlshortener.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chernov.urlshortener.entity.user.UserLevel;


@Repository
public interface UserLevelRepository extends JpaRepository<UserLevel, String> {
}
