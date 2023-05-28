package ru.chernov.urlshortener.entity;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.chernov.urlshortener.converter.OperationTypeConverter;
import ru.chernov.urlshortener.enums.operation.OperationType;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "operation")
public class Operation {

    @Id
    @SequenceGenerator(name = "operation_sequence", sequenceName = "operation_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "operation_sequence")
    private Long id;

    @NotNull
    private LocalDateTime createdAt;

    @Convert(converter = OperationTypeConverter.class)
    @NotNull
    private OperationType type;

}
