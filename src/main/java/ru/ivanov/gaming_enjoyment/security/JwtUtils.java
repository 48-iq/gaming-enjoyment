package ru.ivanov.gaming_enjoyment.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.ivanov.gaming_enjoyment.dto.UserDto;
import ru.ivanov.gaming_enjoyment.entities.User;

import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${token.key}")
    private String tokenKey;

    public String generateToken(UserDto user) {
        Date expirationDate= Date.from(ZonedDateTime.now().plusDays(4).toInstant());
        return JWT.create()
                .withSubject("User details")
                .withClaim("id", user.getId())
                .withClaim("username", user.getUsername())
                .withClaim("role", user.getRole())
                .withClaim("password", user.getPassword())
                .withIssuedAt(new Date())
                .withExpiresAt(expirationDate)
                .withIssuer("spring-app-IntelliLearn")
                .sign(Algorithm.HMAC256(tokenKey));

    }

    public String validateTokenAndRetrieveUsername(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(tokenKey))
                .withSubject("User details")
                .withIssuer("spring-app-IntelliLearn")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("username").asString();
    }
}
