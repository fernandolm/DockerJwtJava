package com.ticketreceiver.infrastructure.mapper;

import com.ticketreceiver.domain.dto.TicketDTO;
import com.ticketreceiver.domain.dto.specification.TicketReportDto;
import com.ticketreceiver.domain.entity.Ticket;
import com.ticketreceiver.domain.entity.Ticket.TicketBuilder;
import com.ticketreceiver.domain.response.ReportResponse;
import com.ticketreceiver.domain.response.ReportResponse.ReportResponseBuilder;
import com.ticketreceiver.domain.response.TicketResponse;
import com.ticketreceiver.domain.response.TicketResponse.TicketResponseBuilder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-22T15:27:29-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class TicketMapperImpl implements TicketMapper {

    private final DatatypeFactory datatypeFactory;

    public TicketMapperImpl() {
        try {
            datatypeFactory = DatatypeFactory.newInstance();
        }
        catch ( DatatypeConfigurationException ex ) {
            throw new RuntimeException( ex );
        }
    }

    @Override
    public Ticket mapDtoToEntity(TicketDTO ticketDTO) {
        if ( ticketDTO == null ) {
            return null;
        }

        TicketBuilder<?, ?> ticket = Ticket.builder();

        ticket.date( ticketDTO.getDate() );
        ticket.authorizer( ticketDTO.getAuthorizer() );
        ticket.customer( ticketDTO.getCustomer() );
        ticket.value( ticketDTO.getValue() );

        return ticket.build();
    }

    @Override
    public TicketResponse mapEntityToResponse(Ticket ticket) {
        if ( ticket == null ) {
            return null;
        }

        TicketResponseBuilder<?, ?> ticketResponse = TicketResponse.builder();

        ticketResponse.date( xmlGregorianCalendarToLocalDate( localDateTimeToXmlGregorianCalendar( ticket.getDate() ) ) );
        ticketResponse.authorizer( ticket.getAuthorizer() );
        ticketResponse.customer( ticket.getCustomer() );
        ticketResponse.value( ticket.getValue() );

        return ticketResponse.build();
    }

    @Override
    public List<ReportResponse> mapDtoListToReportList(List<TicketReportDto> ticket) {
        if ( ticket == null ) {
            return null;
        }

        List<ReportResponse> list = new ArrayList<ReportResponse>( ticket.size() );
        for ( TicketReportDto ticketReportDto : ticket ) {
            list.add( ticketReportDtoToReportResponse( ticketReportDto ) );
        }

        return list;
    }

    private static LocalDate xmlGregorianCalendarToLocalDate( XMLGregorianCalendar xcal ) {
        if ( xcal == null ) {
            return null;
        }

        return LocalDate.of( xcal.getYear(), xcal.getMonth(), xcal.getDay() );
    }

    private XMLGregorianCalendar localDateTimeToXmlGregorianCalendar( LocalDateTime localDateTime ) {
        if ( localDateTime == null ) {
            return null;
        }

        return datatypeFactory.newXMLGregorianCalendar(
            localDateTime.getYear(),
            localDateTime.getMonthValue(),
            localDateTime.getDayOfMonth(),
            localDateTime.getHour(),
            localDateTime.getMinute(),
            localDateTime.getSecond(),
            localDateTime.get( ChronoField.MILLI_OF_SECOND ),
            DatatypeConstants.FIELD_UNDEFINED );
    }

    protected ReportResponse ticketReportDtoToReportResponse(TicketReportDto ticketReportDto) {
        if ( ticketReportDto == null ) {
            return null;
        }

        ReportResponseBuilder<?, ?> reportResponse = ReportResponse.builder();

        reportResponse.authorizer( ticketReportDto.getAuthorizer() );
        reportResponse.customer( ticketReportDto.getCustomer() );
        reportResponse.value( ticketReportDto.getValue() );

        return reportResponse.build();
    }
}
