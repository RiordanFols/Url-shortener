package ru.chernov.urlshortener.config.security.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.impl.DefaultJwtBuilder;
import org.springframework.stereotype.Component;


@Component
public class CustomJwtBuilder extends DefaultJwtBuilder {
    private final ObjectMapper objectMapper;


    public CustomJwtBuilder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @Override
    protected byte[] toJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsBytes(object);
    }

}
