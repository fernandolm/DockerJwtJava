package com.ticketreceiver.infrastructure.mapper;

import com.ticketreceiver.domain.dto.specification.TicketReportDto;
import com.ticketreceiver.domain.response.ReportResponse;
import com.ticketreceiver.domain.response.TicketResponse;
import com.ticketreceiver.domain.dto.TicketDTO;
import com.ticketreceiver.domain.entity.Ticket;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    Ticket mapDtoToEntity(TicketDTO ticketDTO);
    TicketResponse mapEntityToResponse(Ticket ticket);
    List<ReportResponse> mapDtoListToReportList(List<TicketReportDto> ticket);
}
