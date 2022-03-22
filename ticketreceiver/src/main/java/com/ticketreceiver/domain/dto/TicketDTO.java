package com.ticketreceiver.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ticketreceiver.domain.enumeration.AuthorizerType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {
    @JsonProperty("data")
    @Schema(name = "date", type = "LocalDateTime", format = "yyyy-MM-ddThh:mm:ss", description = "date value", required = true, example = "2022-03-18T01:55:15.578541")
    private LocalDateTime date;

    @JsonProperty("operadora")
    @Schema(name = "authorizer", type = "AuthorizerType", description = "authorizer", required = true, example = "MASTERCARD", allowableValues = "VISA,MASTERCARD,ELO,AMERICANEXPRESS")
    private AuthorizerType authorizer;

    @NotBlank
    @Pattern(regexp = "^client{1}.+$", flags = Pattern.Flag.CASE_INSENSITIVE)
    @JsonProperty("cliente")
    @Schema(name = "customer", type = "String", description = "customer", required = true, example = "ClientVDZWA02")
    @Size(min = 1, max = 255)
    private String customer;

    @PositiveOrZero
    @JsonProperty("valor")
    @Schema(name = "value", type = "BigDecimal", description = "value", required = true, example = "39.78240828799425")
    @Min(value = 1)
    @Max(value = 2147483647)
    private BigDecimal value;
}
