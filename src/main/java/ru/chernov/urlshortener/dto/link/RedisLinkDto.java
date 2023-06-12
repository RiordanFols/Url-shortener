package ru.chernov.urlshortener.dto.link;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;


@Data
public class RedisLinkDto implements Serializable {
    private String link;
    private UUID token;

}
