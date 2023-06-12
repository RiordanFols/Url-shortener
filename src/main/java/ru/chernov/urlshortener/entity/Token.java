package ru.chernov.urlshortener.entity;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.chernov.urlshortener.converter.TokenStatusConverter;
import ru.chernov.urlshortener.entity.user.User;
import ru.chernov.urlshortener.enums.token.TokenStatus;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "token")
public class Token {

    @Id
    @Size(max = 36)
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @Size(max = 50)
    @NotNull
    private String name;

    @Convert(converter = TokenStatusConverter.class)
    @NotNull
    private TokenStatus status;

    @NotNull
    private LocalDateTime createdAt;

}
