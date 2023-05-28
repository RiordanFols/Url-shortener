package ru.chernov.urlshortener.service;

import org.springframework.stereotype.Service;
import ru.chernov.urlshortener.entity.Operation;
import ru.chernov.urlshortener.enums.operation.OperationType;
import ru.chernov.urlshortener.repository.OperationRepository;

import java.time.LocalDateTime;

import static java.time.ZoneOffset.UTC;


@Service
public class OperationService {
    private final OperationRepository operationRepository;


    public OperationService(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }


    public void addOperation(OperationType operationType) {
        var operation = new Operation();
        operation.setCreatedAt(LocalDateTime.now(UTC));
        operation.setType(operationType);
        operationRepository.save(operation);
    }

}
