package ru.chernov.urlshortener.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chernov.urlshortener.entity.Token;

import java.util.Optional;


@Repository
public interface TokenRepository extends JpaRepository<Token, String> {

    @EntityGraph(attributePaths = {"user", "user.level"})
    Optional<Token> findByValue(String value);

}
