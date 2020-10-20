package com.mycompany.hw_l28_spring_integration.config;

import com.mycompany.hw_l28_spring_integration.domain.TicketRequest;
import com.mycompany.hw_l28_spring_integration.domain.TicketResponse;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import java.util.List;

@MessagingGateway
public interface TicketIntegration {

    @Gateway(requestChannel = "requestTicketChannel", replyChannel = "responseTicketChannel")
    List<TicketResponse> buyTickets(List<TicketRequest> ticketsRequest);

}
