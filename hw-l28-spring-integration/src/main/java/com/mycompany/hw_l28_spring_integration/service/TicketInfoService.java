package com.mycompany.hw_l28_spring_integration.service;

import com.mycompany.hw_l28_spring_integration.domain.Ticket;
import com.mycompany.hw_l28_spring_integration.domain.TicketRequest;
import com.mycompany.hw_l28_spring_integration.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketInfoService {

    private final TicketRepository ticketRepository;

    public boolean ticketsAvailable(TicketRequest ticketRequest) {
        List<Ticket> tickets = ticketRepository.findTicketsByFlightId(ticketRequest.getFlight().getId());
        return tickets.size() < 5;
    }
}
