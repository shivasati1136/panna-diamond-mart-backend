package com.panna.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private static final String SECRET =
            "mysecretkeymysecretkeymysecretkey12345";

    private Key getSignKey() {

        return Keys.hmacShaKeyFor(
                SECRET.getBytes()
        );
    }

    public String generateToken(String email, String role) {

        Map<String, Object> claims = new HashMap<>();

        claims.put("role", role);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)
                )
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractAllClaims(String token) {

        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractEmail(String token) {

        return extractAllClaims(token)
                .getSubject();
    }
    // GENERATE REFRESH TOKEN
    public String generateRefreshToken(String email) {

        return Jwts.builder()

                .setSubject(email)

                .claim("type", "REFRESH")

                .setIssuedAt(new Date())

                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000L * 60 * 60 * 24 * 7
                        )
                )

                .signWith(getSignKey(), SignatureAlgorithm.HS256)

                .compact();
    }
}