package com.ticketreceiver.infrastructure.repository.specification;

import com.ticketreceiver.domain.dto.specification.TicketReportDto;
import com.ticketreceiver.domain.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query(value = "select t1.authorizer, t2.customer, t1.max_total_sum as value FROM (SELECT authorizer, MAX(total_sum) max_total_sum FROM (SELECT authorizer, customer, SUM(value) AS total_sum FROM Ticket WHERE date >= :date GROUP BY authorizer, customer) AS q1 GROUP BY authorizer) t1 JOIN (SELECT authorizer, customer, SUM(value) AS total_sum FROM Ticket WHERE date >= :date GROUP BY authorizer, customer) AS t2 ON t1.authorizer = t2.authorizer AND t1.max_total_sum = t2.total_sum", nativeQuery = true)
    List<TicketReportDto> findByDate(@Param("date") LocalDateTime date);
}
