package ru.chernov.urlshortener.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.chernov.urlshortener.entity.Operation;
import ru.chernov.urlshortener.entity.Token;
import ru.chernov.urlshortener.enums.operation.OperationType;
import ru.chernov.urlshortener.exception.operation.TooManyMinuteOperationsException;
import ru.chernov.urlshortener.exception.operation.TooManyMonthOperationsException;
import ru.chernov.urlshortener.repository.OperationRepository;

import java.time.LocalDateTime;
import java.util.UUID;

import static ru.chernov.urlshortener.utils.TimeUtil.utcNow;


@Service
public class OperationService {
    private static final Logger logger = LogManager.getLogger(OperationService.class);

    private final OperationRepository operationRepository;
    private final TokenService tokenService;


    public OperationService(OperationRepository operationRepository,
                            TokenService tokenService) {
        this.operationRepository = operationRepository;
        this.tokenService = tokenService;
    }


    public void validateMax(UUID tokenValue) {
        validateMinuteMax(tokenValue);
        validateMonthMax(tokenValue);
    }


    public void addOperation(OperationType operationType, UUID token) {
        var operation = new Operation();
        operation.setCreatedAt(utcNow());
        operation.setType(operationType);
        operation.setToken(tokenService.find(token));
        operationRepository.save(operation);
    }


    private void validateMinuteMax(UUID tokenValue) {
        LocalDateTime createdAtFrom = utcNow().minusMinutes(1);
        Long lastMinuteOperationsCount = operationRepository.countByToken(tokenValue.toString(), createdAtFrom);

        Token token = tokenService.find(tokenValue);
        if (lastMinuteOperationsCount >= token.getUser().getLevel().getMinuteMax()) {
            logger.warn("Operation cannot be done, too many in last minute.");
            throw new TooManyMinuteOperationsException();
        }
    }


    private void validateMonthMax(UUID tokenValue) {
        LocalDateTime createdAtFrom = utcNow().minusMonths(1);
        Long lastMonthOperationsCount = operationRepository.countByToken(tokenValue.toString(), createdAtFrom);

        Token token = tokenService.find(tokenValue);
        if (lastMonthOperationsCount >= token.getUser().getLevel().getMonthMax()) {
            logger.warn("Operation cannot be done, too many in last minute.");
            throw new TooManyMonthOperationsException();
        }
    }

}
