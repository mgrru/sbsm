package com.mgrru.sbsm.entity;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;



@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.exp}")
    private long expTime;

    @Value("${jwt.tokenPrefix}")
    private String tokenPrefix;

    public String generateToken(String id) {
        return tokenPrefix +JWT.create()
            .withSubject(id)
            .withExpiresAt(new Date(System.currentTimeMillis()+expTime))
            .sign(Algorithm.HMAC512(secret));
    }

    public boolean validateToken(String token) {
        try {
            JWT.require(Algorithm.HMAC512(secret))
                .build()
                .verify(token.replace(tokenPrefix, ""));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
