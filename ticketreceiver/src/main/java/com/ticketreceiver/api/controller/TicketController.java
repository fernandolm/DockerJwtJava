package com.ticketreceiver.api.controller;

import com.ticketreceiver.api.controller.openapi.TicketApi;
import com.ticketreceiver.domain.response.TicketResponse;
import com.ticketreceiver.domain.response.ReportResponse;
import com.ticketreceiver.domain.dto.TicketDTO;
import com.ticketreceiver.infrastructure.mapper.TicketMapper;
import com.ticketreceiver.service.specification.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class TicketController implements TicketApi {

    private TicketService ticketService;
    private TicketMapper ticketMapper;

    @Override
    @PostMapping(value = "/log")
    public ResponseEntity<TicketResponse> saveLog(@Valid @RequestBody TicketDTO ticketDto) {
        var ticket = ticketMapper.mapDtoToEntity(ticketDto);

        var ticketSaved = ticketService.saveLog(ticket);

        var ticketResponse = ticketMapper.mapEntityToResponse(ticketSaved);

        if(ticketResponse != null) {
            return ResponseEntity.ok().body(ticketResponse);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    @GetMapping(value = "/client")
    public ResponseEntity<List<ReportResponse>> getSpendMoreMoneyCustomersByAuthorizerReport() {
        var ticketsDto = ticketService.getCustomersSpendMoreMoneyReport();

        var ticketsReport = ticketMapper.mapDtoListToReportList(ticketsDto);

        if(ticketsReport != null && !ticketsReport.isEmpty()) {
            return ResponseEntity.ok().body(ticketsReport);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
