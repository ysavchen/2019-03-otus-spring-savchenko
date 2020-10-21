package com.mycompany.hw_l28_spring_integration.service;

import com.mycompany.hw_l28_spring_integration.domain.Ticket;
import com.mycompany.hw_l28_spring_integration.exception.NoTicketsLeftException;
import com.mycompany.hw_l28_spring_integration.integration.TicketRequest;
import com.mycompany.hw_l28_spring_integration.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketInfoService {

    private final TicketRepository ticketRepository;

    public TicketRequest ticketAvailable(TicketRequest ticketRequest) {
        List<Ticket> tickets = ticketRepository.findTicketsByFlightId(ticketRequest.getFlight().getId());
        if (tickets.size() <= 3) {
            return ticketRequest;
        } else {
            throw new NoTicketsLeftException("No tickets left");
        }
    }
}
