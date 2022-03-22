package com.ticketreceiver.service.specification;

import com.ticketreceiver.domain.dto.specification.TicketReportDto;
import com.ticketreceiver.domain.entity.Ticket;

import java.util.List;

public interface TicketService {
    Ticket saveLog(Ticket ticket);
    List<TicketReportDto> getCustomersSpendMoreMoneyReport();
}
