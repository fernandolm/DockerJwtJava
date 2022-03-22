package com.generator.creditcard.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.generator.creditcard.domain.enumeration.AuthorizerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@SuperBuilder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponse {
    @JsonProperty("data")
    private LocalDate date;

    @JsonProperty("operadora")
    private AuthorizerType authorizer;

    @JsonProperty("cliente")
    private String customer;

    @JsonProperty("valor")
    private BigDecimal value;
}
