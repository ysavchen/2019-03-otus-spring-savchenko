package com.mycompany.hw_l28_spring_integration.service;

import com.mycompany.hw_l28_spring_integration.domain.Ticket;
import com.mycompany.hw_l28_spring_integration.integration.TicketOperation;
import com.mycompany.hw_l28_spring_integration.integration.TicketRequest;
import com.mycompany.hw_l28_spring_integration.integration.TicketResponse;

public interface TicketOperationsService {

    TicketResponse sellTicket(TicketOperation<TicketRequest> ticketOperation);

    boolean cancelTicket(TicketOperation<Ticket> ticketOperation);

}
