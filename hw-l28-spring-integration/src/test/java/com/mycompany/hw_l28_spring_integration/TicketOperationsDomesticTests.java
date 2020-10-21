package com.mycompany.hw_l28_spring_integration;

import com.mycompany.hw_l28_spring_integration.domain.Flight;
import com.mycompany.hw_l28_spring_integration.domain.Ticket;
import com.mycompany.hw_l28_spring_integration.integration.TicketOperation;
import com.mycompany.hw_l28_spring_integration.integration.TicketOperation.Type;
import com.mycompany.hw_l28_spring_integration.integration.TicketRequest;
import com.mycompany.hw_l28_spring_integration.integration.TicketResponse;
import com.mycompany.hw_l28_spring_integration.repositories.TicketRepository;
import com.mycompany.hw_l28_spring_integration.service.TicketOperationsDomestic;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import(TicketOperationsDomestic.class)
public class TicketOperationsDomesticTests {

    @Autowired
    private TicketOperationsDomestic ticketOperations;

    @MockBean
    private TicketRepository ticketRepository;

    private final Ticket ticket = new Ticket();
    private final TicketRequest ticketRequest = new TicketRequest("John Doe", new Flight());
    private final TicketResponse ticketResponse = new TicketResponse("Добро пожаловать на борт!", ticket);

    @Test
    void sellTicketDomestic() {
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        TicketOperation<TicketRequest> operation = new TicketOperation<>(ticketRequest, Type.Sell);
        TicketResponse response = ticketOperations.sellTicket(operation);
        assertEquals(ticketResponse, response);
    }

    @Test
    void cancelTicketDomestic() {
        doNothing().when(ticketRepository).delete(any(Ticket.class));

        TicketOperation<Ticket> operation = new TicketOperation<>(ticket, Type.Cancel);
        assertTrue(ticketOperations.cancelTicket(operation));
    }
}
