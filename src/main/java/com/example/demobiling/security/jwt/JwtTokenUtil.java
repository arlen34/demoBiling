package com.example.demobiling.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;

@Service
@ConfigurationProperties(prefix = "jwt.token")
@Getter
@Setter
public class  JwtTokenUtil {

    private static String secret;

    private static String issuer;

    private int expires;

    public String generateToken(String email) {
        Date expirationDate = Date.from(ZonedDateTime.now().plusDays(60).toInstant());
        return JWT.create()
                .withClaim("email", email)
                .withIssuedAt(new Date())
                .withIssuer(issuer)
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(secret));
    }

    public String validateJwtToken(String jwt) {
        DecodedJWT verify = getDecodedJWT(jwt);

        return verify.getClaim("email").asString();
    }

    private DecodedJWT getDecodedJWT(String jwt) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret))
                .withIssuer(issuer)
                .build();

        return jwtVerifier.verify(jwt);
    }
}
