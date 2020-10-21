package com.mycompany.hw_l28_spring_integration.integration;

import com.mycompany.hw_l28_spring_integration.domain.Ticket;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface TicketIntegration {

    @Gateway(requestChannel = "buyTicketChannel", replyChannel = "responseTicketChannel")
    TicketResponse buyTicket(TicketRequest ticketsRequest);

    @Gateway(requestChannel = "cancelTicketChannel", replyChannel = "responseTicketChannel")
    boolean cancelTicket(Ticket ticket);

}
