package com.mycompany.hw_l28_spring_integration.service;

import com.mycompany.hw_l28_spring_integration.domain.Ticket;
import com.mycompany.hw_l28_spring_integration.domain.TicketRequest;
import com.mycompany.hw_l28_spring_integration.domain.TicketResponse;
import com.mycompany.hw_l28_spring_integration.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketSellingDomestic implements TicketSellingService {

    private final TicketRepository ticketRepository;

    public TicketResponse sellTicket(TicketRequest ticketRequest) {
        var ticket = ticketRepository.save(
                new Ticket(ticketRequest.getPassengerName(), ticketRequest.getFlight())
        );
        return new TicketResponse("Добро пожаловать на борт!", ticket);
    }
}
