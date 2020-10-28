package com.mycompany.hw_l28_spring_integration.service;

import com.mycompany.hw_l28_spring_integration.domain.Ticket;
import com.mycompany.hw_l28_spring_integration.integration.TicketConfirmation;

public interface TicketOperationsService {

    TicketConfirmation sellTicket(Ticket ticket);

    boolean cancelTicket(Ticket ticket);

}
