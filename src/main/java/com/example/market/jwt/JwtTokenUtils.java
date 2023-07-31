package com.example.market.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.sql.Date;
import java.time.Instant;
import java.util.Base64;

@Component
public class JwtTokenUtils {
    private final Key key;

    public JwtTokenUtils(@Value("${jwt.secret}") String jwtSecret) {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    // JWT 발급
    public String generateToken(UserDetails userDetails) {
        Claims jwtClaims = Jwts.claims()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusSeconds(10)));

        return Jwts.builder()
                .setClaims(jwtClaims)
                .signWith(key)
                .compact();
    }
}
