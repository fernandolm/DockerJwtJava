package com.ticketreceiver.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ticketreceiver.domain.enumeration.AuthorizerType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@SuperBuilder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketResponse {
    @JsonProperty("data")
    @Schema(name = "date", type = "LocalDateTime", format = "yyyy-MM-ddThh:mm:ss", description = "date value", required = true, example = "2022-03-18T01:55:15.578541")
    private LocalDate date;

    @JsonProperty("operadora")
    @Schema(name = "authorizer", type = "AuthorizerType", description = "authorizer", required = true, example = "MASTERCARD", allowableValues = "VISA,MASTERCARD,ELO,AMERICANEXPRESS")
    private AuthorizerType authorizer;

    @JsonProperty("cliente")
    @Schema(name = "customer", type = "String", description = "customer", required = true, example = "ClientVDZWA02")
    private String customer;

    @JsonProperty("valor")
    @Schema(name = "value", type = "BigDecimal", description = "value", required = true, example = "39.78240828799425")
    private BigDecimal value;
}
