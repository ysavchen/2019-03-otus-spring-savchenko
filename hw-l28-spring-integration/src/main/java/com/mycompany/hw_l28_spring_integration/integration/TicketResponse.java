package com.mycompany.hw_l28_spring_integration.integration;

import com.mycompany.hw_l28_spring_integration.domain.Ticket;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TicketResponse {

    private final String welcomeMessage;
    private final Ticket ticket;

}
