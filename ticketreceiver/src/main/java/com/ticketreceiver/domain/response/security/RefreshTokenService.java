package com.ticketreceiver.domain.response.security;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RefreshTokenService {
    public RefreshTokenResponse generateRefreshToken() {
        RefreshTokenResponse refreshToken = new RefreshTokenResponse();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());

        return refreshToken;
    }
}
