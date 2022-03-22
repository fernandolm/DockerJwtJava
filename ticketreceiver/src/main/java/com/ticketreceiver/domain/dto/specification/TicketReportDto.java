package com.ticketreceiver.domain.dto.specification;

import com.ticketreceiver.domain.enumeration.AuthorizerType;

import javax.persistence.Cacheable;
import java.math.BigDecimal;

@Cacheable()
public interface TicketReportDto {
    AuthorizerType getAuthorizer();
    String getCustomer();
    BigDecimal getValue();
}
