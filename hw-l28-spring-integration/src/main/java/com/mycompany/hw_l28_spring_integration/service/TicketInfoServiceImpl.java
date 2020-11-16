package com.mycompany.hw_l28_spring_integration.service;

import com.mycompany.hw_l28_spring_integration.domain.Ticket;
import com.mycompany.hw_l28_spring_integration.exception.NoTicketsLeftException;
import com.mycompany.hw_l28_spring_integration.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketInfoServiceImpl implements TicketInfoService {

    private static final int NUM_AVAILABLE_TICKETS = 3;

    private final TicketRepository ticketRepository;

    public Ticket ticketAvailable(Ticket ticket) {
        List<Ticket> tickets = ticketRepository.findTicketsByFlightId(ticket.getFlight().getId());
        if (tickets.size() < NUM_AVAILABLE_TICKETS) {
            return ticket;
        } else {
            throw new NoTicketsLeftException("No tickets left");
        }
    }
}
