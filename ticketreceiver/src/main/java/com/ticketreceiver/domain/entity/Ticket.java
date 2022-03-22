package com.ticketreceiver.domain.entity;

import com.ticketreceiver.domain.enumeration.AuthorizerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //data
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    //operadora
    private AuthorizerType authorizer;

    @NotBlank
    @Pattern(regexp = "^client{1}.+$", flags = Pattern.Flag.CASE_INSENSITIVE)
    @Size(min = 1, max = 255)
    //cliente
    private String customer;

    @PositiveOrZero
    //valor
    private BigDecimal value;
}
