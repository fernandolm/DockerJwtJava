package com.ticketreceiver.api.controller.openapi;

import com.ticketreceiver.domain.dto.security.LoginRequestDto;
import com.ticketreceiver.domain.response.security.AuthenticationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Api - Authorization", description = "Authorization")
public interface AuthorizationApi {
    @Operation(summary = "Authorization Token",
            description = "Get the jwt token",
            tags = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jwt token was gotten with success.",
                    content = @Content(schema = @Schema(implementation = AuthenticationResponse.class))),
            @ApiResponse(responseCode = "400", description = "Fail to get jwt token."),
            @ApiResponse(responseCode = "401", description = "Need authentication."),
            @ApiResponse(responseCode = "500", description = "Server error. Server down.")})
    @PostMapping(value = "/login")
    AuthenticationResponse login(@Parameter(description = "LoginRequest", required = true,
            schema = @Schema(implementation = LoginRequestDto.class))
                                 @RequestBody LoginRequestDto loginRequestDto);
}
