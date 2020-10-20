package com.mycompany.hw_l28_spring_integration.service;

import com.mycompany.hw_l28_spring_integration.domain.TicketRequest;
import com.mycompany.hw_l28_spring_integration.domain.TicketResponse;

public interface TicketSellingService {

    TicketResponse sellTicket(TicketRequest ticketRequest);

}
