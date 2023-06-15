package ru.chernov.urlshortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.chernov.urlshortener.entity.Operation;

import java.time.LocalDateTime;


@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

    @Query("SELECT count(op) FROM Operation op " +
            "WHERE op.token.value = :token " +
            "AND op.createdAt > :createdAtFrom")
    Long countByToken(@Param("token") String token,
                      @Param("createdAtFrom") LocalDateTime createdAtFrom);

}
