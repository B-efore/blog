package com.jiwon.blog.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTUtil {

    private final SecretKey ACCESS_SECRET_KEY;
    private final SecretKey REFRESH_SECRET_KEY;
    private final long EXPIRED_MS = 1000L * 60 * 60;

    public JWTUtil(@Value("${spring.jwt.access-secret}") String ACCESS_SECRET_KEY,
                   @Value("${spring.jwt.refresh-secret}") String REFRESH_SECRET_KEY) {
        this.ACCESS_SECRET_KEY = new SecretKeySpec(ACCESS_SECRET_KEY.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
        this.REFRESH_SECRET_KEY = new SecretKeySpec(REFRESH_SECRET_KEY.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public String getEmail(String token) {
        return Jwts.parser().verifyWith(ACCESS_SECRET_KEY).build().parseSignedClaims(token).getPayload().get("email", String.class);
    }

    public String getRole(String token) {
        return Jwts.parser().verifyWith(ACCESS_SECRET_KEY).build().parseSignedClaims(token).getPayload().get("role", String.class);
    }

    public boolean isExpired(String token) {
        return Jwts.parser().verifyWith(ACCESS_SECRET_KEY).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    public String createJwt(Authentication authentication) {

        String email = authentication.getName();
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        Date now = new Date(System.currentTimeMillis());

        return Jwts.builder()
                .claim("email", email)
                .claim("role", role)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + EXPIRED_MS))
                .signWith(ACCESS_SECRET_KEY)
                .compact();
    }
}
