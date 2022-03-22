package com.ticketreceiver.domain.response.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenResponse {
    private Long id;
    private String token;
    private Instant createdDate;
}
