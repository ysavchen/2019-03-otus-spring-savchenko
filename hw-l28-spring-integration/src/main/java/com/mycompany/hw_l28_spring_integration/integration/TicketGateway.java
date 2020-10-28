package com.mycompany.hw_l28_spring_integration.integration;

import com.mycompany.hw_l28_spring_integration.domain.Ticket;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface TicketGateway {

    @Gateway(requestChannel = "buyTicketChannel", replyChannel = "responseTicketChannel")
    TicketConfirmation buyTicket(Ticket ticket);

    @Gateway(requestChannel = "cancelTicketChannel", replyChannel = "responseTicketChannel")
    boolean cancelTicket(Ticket ticket);

}
