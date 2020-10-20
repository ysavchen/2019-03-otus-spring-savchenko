package com.mycompany.hw_l28_spring_integration.repositories;

import com.mycompany.hw_l28_spring_integration.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, String> {

    List<Ticket> findTicketsByFlightId(Long id);
}
