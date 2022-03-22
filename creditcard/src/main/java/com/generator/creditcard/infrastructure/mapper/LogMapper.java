package com.generator.creditcard.infrastructure.mapper;

import com.generator.creditcard.domain.Ticket;
import com.generator.creditcard.domain.request.Log;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LogMapper {
    Log map(Ticket ticket);
}
