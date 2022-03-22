package com.ticketreceiver.service;

import com.ticketreceiver.domain.dto.specification.TicketReportDto;
import com.ticketreceiver.domain.entity.Ticket;
import com.ticketreceiver.infrastructure.mapper.TicketMapper;
import com.ticketreceiver.infrastructure.properties.ApplicationProperties;
import com.ticketreceiver.infrastructure.repository.specification.TicketRepository;
import com.ticketreceiver.service.specification.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;
    private ApplicationProperties applicationProperties;
    private TicketMapper ticketMapper;

    @Override
    public Ticket saveLog(Ticket ticket) {
        var ticketSaved = ticketRepository.save(ticket);

        return ticketSaved;
    }

    @Override
    public List<TicketReportDto> getCustomersSpendMoreMoneyReport() {
        LocalDateTime filterDate = LocalDateTime.now().minusMinutes(applicationProperties.getReport().getLastMinutes());
        var tickets = ticketRepository.findByDate(filterDate);

        return tickets;
    }
}
