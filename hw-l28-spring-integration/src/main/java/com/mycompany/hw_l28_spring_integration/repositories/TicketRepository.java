package com.mycompany.hw_l28_spring_integration.repositories;

import com.mycompany.hw_l28_spring_integration.domain.Flight;
import com.mycompany.hw_l28_spring_integration.domain.Ticket;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, String> {

    List<Ticket> findTicketsByFlightId(Long id);

    @EntityGraph("ticket-entity-graph")
    List<Ticket> findTicketsByPassengerNameAndFlight(String passengerName, Flight flight);
}
