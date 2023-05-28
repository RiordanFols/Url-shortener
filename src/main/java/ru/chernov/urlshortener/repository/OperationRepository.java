package ru.chernov.urlshortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chernov.urlshortener.entity.Operation;


@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
}
