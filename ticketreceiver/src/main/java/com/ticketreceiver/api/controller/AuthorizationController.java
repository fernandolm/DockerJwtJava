package com.ticketreceiver.api.controller;

import com.ticketreceiver.api.controller.openapi.AuthorizationApi;
import com.ticketreceiver.domain.dto.security.LoginRequestDto;
import com.ticketreceiver.domain.response.security.AuthenticationResponse;
import com.ticketreceiver.service.security.AuthorizationService;
import com.ticketreceiver.service.security.GenerateKeyService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AuthorizationController implements AuthorizationApi {
    private final AuthorizationService authorizationService;
    private final GenerateKeyService generateKeyService;

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequestDto loginRequestDto) {
        return authorizationService.login(loginRequestDto);
    }
}
