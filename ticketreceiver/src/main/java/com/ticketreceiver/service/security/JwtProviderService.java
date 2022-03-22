package com.ticketreceiver.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.util.*;

@Service
@RequiredArgsConstructor
public class JwtProviderService {
    @Value("${jwt.expiration.time}")
    private Long jwtExpirationInMillis;

    public String generateToken(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return generateTokenWithUserName(principal.getUsername());
    }

    public String generateTokenWithUserName(String username) {
        final String SECRET_ENCODED64_KEY = "OGQ3MDhhZmUtMjk2Ni00MGI3LTkxOGMtYTM5NTUxNjI1OTU4";

        Key jwtKey = new SecretKeySpec(Base64.getDecoder().decode(SECRET_ENCODED64_KEY),
                SignatureAlgorithm.HS256.getJcaName());

        String jwtToken = Jwts.builder()
                .setSubject(username)
                .setAudience("everyone")
                .setIssuer("self")
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
                .signWith(jwtKey).compact();

        return jwtToken;

    }

    public Long getJwtExpirationInMillis() {
        return jwtExpirationInMillis;
    }
}
