package com.mycompany.hw_l28_spring_integration.service;

import com.mycompany.hw_l28_spring_integration.domain.Ticket;
import com.mycompany.hw_l28_spring_integration.integration.TicketConfirmation;
import com.mycompany.hw_l28_spring_integration.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketOperationsDomestic implements TicketOperationsService {

    private final TicketRepository ticketRepository;

    public TicketConfirmation sellTicket(Ticket ticket) {
        var savedTicket = ticketRepository.save(ticket);
        return new TicketConfirmation("Добро пожаловать на борт!", savedTicket);
    }

    public boolean cancelTicket(Ticket ticket) {
        ticketRepository.delete(ticket);
        return true;
    }
}
