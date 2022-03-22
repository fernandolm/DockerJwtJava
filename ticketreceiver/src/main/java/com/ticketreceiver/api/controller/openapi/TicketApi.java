package com.ticketreceiver.api.controller.openapi;

import com.ticketreceiver.domain.response.TicketResponse;
import com.ticketreceiver.domain.response.ReportResponse;
import com.ticketreceiver.domain.dto.TicketDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Api - Log", description = "Ticket reader and report Api")
public interface TicketApi {
    @Operation(summary = "Ticket receiver",
            description = "Stores the ticket in database",
            tags = { })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ticket have been stored with success.",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = TicketResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Fail to save the ticket."),
            @ApiResponse(responseCode = "401", description = "Need authentication."),
            @ApiResponse(responseCode = "500", description = "Server error. Server down.")})
    @PostMapping(value = "/log")
    ResponseEntity<TicketResponse> saveLog(@Parameter(description="Ticket", required=true,
            schema=@Schema(implementation = TicketDTO.class))
            @Valid @RequestBody TicketDTO ticketDto);

    @Operation(summary = "Ticket report",
            description = "Gets the customers by authorizer who spent more money in the last 30 minutes",
            tags = { })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Report loaded with success.",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ReportResponse.class)))),
            @ApiResponse(responseCode = "204", description = "No data to be presented."),
            @ApiResponse(responseCode = "401", description = "Need authentication."),
            @ApiResponse(responseCode = "500", description = "Fail to load the report. Server down.") })
    @GetMapping(value = "/client")
    ResponseEntity<List<ReportResponse>> getSpendMoreMoneyCustomersByAuthorizerReport();
}
