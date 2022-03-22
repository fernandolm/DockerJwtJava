package com.generator.creditcard.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.generator.creditcard.domain.enumeration.AuthorizerType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Log {
    @JsonProperty("data")
    private LocalDateTime date;

    @JsonProperty("operadora")
    private AuthorizerType authorizer;

    @JsonProperty("cliente")
    private String customer;

    @JsonProperty("valor")
    private BigDecimal value;
}
