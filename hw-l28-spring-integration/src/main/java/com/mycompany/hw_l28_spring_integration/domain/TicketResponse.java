package com.mycompany.hw_l28_spring_integration.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TicketResponse {

    private final String welcomeMessage;
    private final Ticket ticket;

}
