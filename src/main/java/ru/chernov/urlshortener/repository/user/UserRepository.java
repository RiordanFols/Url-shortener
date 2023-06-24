package ru.chernov.urlshortener.repository.user;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chernov.urlshortener.entity.user.User;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);

    @EntityGraph(attributePaths = {"userRoles"})
    Optional<User> findByUsername(String username);

}
