package com.mycompany.hw_l28_spring_integration.service;

import com.mycompany.hw_l28_spring_integration.domain.Ticket;
import com.mycompany.hw_l28_spring_integration.integration.TicketOperation;
import com.mycompany.hw_l28_spring_integration.integration.TicketRequest;
import com.mycompany.hw_l28_spring_integration.integration.TicketResponse;
import com.mycompany.hw_l28_spring_integration.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketOperationsInternational implements TicketOperationsService {

    private final TicketRepository ticketRepository;

    public TicketResponse sellTicket(TicketOperation<TicketRequest> ticketOperation) {
        TicketRequest request = ticketOperation.getObject();
        var ticket = ticketRepository.save(
                new Ticket(request.getPassengerName(), request.getFlight())
        );
        return new TicketResponse("Welcome on Board!", ticket);
    }

    public boolean cancelTicket(TicketOperation<Ticket> ticketOperation) {
        Ticket ticket = ticketOperation.getObject();
        ticketRepository.delete(ticket);
        return true;
    }
}
