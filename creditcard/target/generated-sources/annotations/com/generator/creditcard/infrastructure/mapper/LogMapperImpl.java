package com.generator.creditcard.infrastructure.mapper;

import com.generator.creditcard.domain.Ticket;
import com.generator.creditcard.domain.request.Log;
import com.generator.creditcard.domain.request.Log.LogBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-22T15:25:30-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class LogMapperImpl implements LogMapper {

    @Override
    public Log map(Ticket ticket) {
        if ( ticket == null ) {
            return null;
        }

        LogBuilder log = Log.builder();

        log.date( ticket.getDate() );
        log.authorizer( ticket.getAuthorizer() );
        log.customer( ticket.getCustomer() );
        log.value( ticket.getValue() );

        return log.build();
    }
}
