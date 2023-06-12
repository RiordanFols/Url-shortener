package ru.chernov.urlshortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chernov.urlshortener.entity.Token;


@Repository
public interface TokenRepository extends JpaRepository<Token, String> {
}
