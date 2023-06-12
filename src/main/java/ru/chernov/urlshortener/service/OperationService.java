package ru.chernov.urlshortener.service;

import org.springframework.stereotype.Service;
import ru.chernov.urlshortener.entity.Operation;
import ru.chernov.urlshortener.enums.operation.OperationType;
import ru.chernov.urlshortener.repository.OperationRepository;

import java.util.UUID;

import static ru.chernov.urlshortener.utils.TimeUtil.utcNow;


@Service
public class OperationService {
    private final OperationRepository operationRepository;
    private final TokenService tokenService;


    public OperationService(OperationRepository operationRepository,
                            TokenService tokenService) {
        this.operationRepository = operationRepository;
        this.tokenService = tokenService;
    }


    public void addOperation(OperationType operationType, UUID token) {
        var operation = new Operation();
        operation.setCreatedAt(utcNow());
        operation.setType(operationType);
        operation.setToken(tokenService.find(token));
        operationRepository.save(operation);
    }

}
