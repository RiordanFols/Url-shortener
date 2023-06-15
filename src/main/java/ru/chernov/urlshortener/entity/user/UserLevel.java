package ru.chernov.urlshortener.entity.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;


@Entity
@Table(name = "user_level")
@Data
public class UserLevel {

    @Id
    @Size(max = 30)
    private String name;

    @NotNull
    private Long minuteMax;

    @NotNull
    private Long monthMax;

    @NotNull
    private BigDecimal price;

}
