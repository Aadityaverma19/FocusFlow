package com.focusflow.focusflow_backend.service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("mysupersecretkeymysupersecretkey1232378".getBytes());

    public String generateToken(String email){
        return Jwts.builder()
        .claims()
            .subject(email)
            .issuedAt(new Date())
            .expiration(new Date( System.currentTimeMillis() + 86400000 ))
        .and()
        .signWith(SECRET_KEY)
        .compact();
    }

    public String extractEmail(String token){
        return Jwts.parser()
        .verifyWith(SECRET_KEY)
        .build()
        .parseSignedClaims(token)
        .getPayload()
        .getSubject();
    }
}
