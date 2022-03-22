package com.ticketreceiver.service.security;

import com.ticketreceiver.domain.dto.security.LoginRequestDto;
import com.ticketreceiver.domain.response.security.AuthenticationResponse;
import com.ticketreceiver.domain.response.security.RefreshTokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
@Slf4j
public class AuthorizationService {

    private final AuthenticationManager authenticationManager;
    private final JwtProviderService jwtProviderService;
    private final RefreshTokenService refreshTokenService;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsServiceImpl userDetailsService;

    public String getEncodedPassword() {
        String encodedPassword = passwordEncoder.encode("test_password");
        log.info(">>> Encoded Password: " + encodedPassword);
        return encodedPassword;
    }

    public AuthenticationResponse login(LoginRequestDto loginRequestDto) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequestDto.getUsername());

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDetails, loginRequestDto.getPassword(), userDetails.getAuthorities()));

        SecurityContextHolder.getContext().setAuthentication(authenticate);

        String token = jwtProviderService.generateToken(authenticate);

        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                                                        .authenticationToken(token)
                                                        .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                                                        .expiresAt(Instant.now().plusMillis(jwtProviderService.getJwtExpirationInMillis()))
                                                        .username(loginRequestDto.getUsername())
                                                        .build();

        return authenticationResponse;
    }
}
