package ru.chernov.urlshortener.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chernov.urlshortener.entity.user.UserRole;
import ru.chernov.urlshortener.entity.user.UserRoleId;


@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
}
