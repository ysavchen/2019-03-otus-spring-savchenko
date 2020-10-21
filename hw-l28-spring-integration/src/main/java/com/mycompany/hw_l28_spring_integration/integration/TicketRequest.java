package com.mycompany.hw_l28_spring_integration.integration;

import com.mycompany.hw_l28_spring_integration.domain.Flight;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TicketRequest {

    private final String passengerName;
    private final Flight flight;

}
