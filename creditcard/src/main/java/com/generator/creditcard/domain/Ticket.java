package com.generator.creditcard.domain;

import com.generator.creditcard.domain.enumeration.AuthorizerType;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SuperBuilder
@Data
public class Ticket {
    //data
    private LocalDateTime date;
    //operadora
    private AuthorizerType authorizer;
    //cliente
    private String customer;
    //valor
    private BigDecimal value;
}
