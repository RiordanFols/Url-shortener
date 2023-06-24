package ru.chernov.urlshortener.config.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.chernov.urlshortener.config.properties.JwtProperties;
import ru.chernov.urlshortener.entity.user.User;
import ru.chernov.urlshortener.exception.jwt.JwtExpiredException;
import ru.chernov.urlshortener.service.user.UserService;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.time.ZoneOffset.UTC;
import static ru.chernov.urlshortener.utils.TimeUtil.utcNow;


@Service
public class JwtService {
    private static final Logger logger = LogManager.getLogger(JwtService.class);
    private static final String AUTHORITIES = "authorities";

    private final UserService userService;
    private final JwtProperties jwtProperties;


    public JwtService(UserService userService,
                      JwtProperties jwtProperties) {
        this.userService = userService;
        this.jwtProperties = jwtProperties;
    }


    public User getUser(String jwt) {
        Claims claims = extractAllClaims(jwt);

        LocalDateTime expireAt = LocalDateTime.ofInstant(claims.getExpiration().toInstant(), UTC.normalized());
        if (expireAt.isAfter(utcNow())) {
            logger.error("Token [{}] expired", jwt);
            throw new JwtExpiredException();
        }

        return userService.loadUserByUsername(claims.getSubject());
    }


    public String create(String username, String password) {
        User user = userService.find(username, password);

        Map<String, Object> claims = new HashMap<>();
        claims.put(AUTHORITIES, user.getAuthorities());

        LocalDateTime expireAt = utcNow().plusMinutes(jwtProperties.getExpirationMinutes());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(Date.from(utcNow().toInstant(UTC)))
                .setExpiration(Date.from(expireAt.toInstant(UTC)))
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey()).compact();
    }


    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJwt(token)
                .getBody();
    }

}
