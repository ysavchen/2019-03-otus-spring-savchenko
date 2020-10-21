package com.mycompany.hw_l28_spring_integration.shell;

import com.mycompany.hw_l28_spring_integration.domain.Flight;
import com.mycompany.hw_l28_spring_integration.domain.Ticket;
import com.mycompany.hw_l28_spring_integration.integration.TicketIntegration;
import com.mycompany.hw_l28_spring_integration.integration.TicketRequest;
import com.mycompany.hw_l28_spring_integration.repositories.FlightRepository;
import com.mycompany.hw_l28_spring_integration.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import javax.persistence.EntityNotFoundException;

@ShellComponent
@RequiredArgsConstructor
public class TicketCommands {

    private final TicketIntegration ticketIntegration;
    private final FlightRepository flightRepository;
    private final TicketRepository ticketRepository;

    @ShellMethod(value = "Buy ticket", key = {"bt", "buy-ticket"})
    public String buyTicket(@ShellOption(defaultValue = "Moscow") String departureCity,
                            @ShellOption(defaultValue = "Saint Petersburg") String arrivalCity,
                            @ShellOption(defaultValue = "John Doe") String passengerName) {

        Flight flight = flightRepository.findFlight(departureCity, arrivalCity)
                .orElseThrow(() -> new EntityNotFoundException("Flight is not found"));

        var ticketResponse = ticketIntegration.buyTicket(new TicketRequest(passengerName, flight));
        return ticketResponse.toString();
    }

    @ShellMethod(value = "Cancel ticket", key = {"ct", "cancel-ticket"})
    public String cancelTicket(@ShellOption(defaultValue = "Moscow") String departureCity,
                               @ShellOption(defaultValue = "Saint Petersburg") String arrivalCity,
                               @ShellOption(defaultValue = "John Doe") String passengerName) {

        Flight flight = flightRepository.findFlight(departureCity, arrivalCity)
                .orElseThrow(() -> new EntityNotFoundException("Flight is not found"));
        Ticket ticket = ticketRepository.findTicketsByPassengerNameAndFlight(passengerName, flight)
                .stream().findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Ticket is not found"));

        boolean isCancelled = ticketIntegration.cancelTicket(ticket);
        return "Ticket (id = " + ticket.getTicketNo() + ") is cancelled: " + isCancelled;
    }
}
