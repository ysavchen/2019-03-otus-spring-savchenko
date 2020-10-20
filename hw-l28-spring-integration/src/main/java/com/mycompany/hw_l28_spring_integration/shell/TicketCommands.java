package com.mycompany.hw_l28_spring_integration.shell;

import com.mycompany.hw_l28_spring_integration.config.TicketIntegration;
import com.mycompany.hw_l28_spring_integration.domain.Flight;
import com.mycompany.hw_l28_spring_integration.domain.TicketRequest;
import com.mycompany.hw_l28_spring_integration.domain.TicketResponse;
import com.mycompany.hw_l28_spring_integration.repositories.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static java.util.stream.Collectors.joining;

@ShellComponent
@RequiredArgsConstructor
public class TicketCommands {

    private final TicketIntegration ticketIntegration;
    private final FlightRepository flightRepository;

    @ShellMethod(value = "Buy ticket", key = {"bt", "buy-ticket"})
    public String buyTicket(@ShellOption(defaultValue = "Moscow") String departureCity,
                            @ShellOption(defaultValue = "Saint Petersburg") String arrivalCity,
                            @ShellOption(defaultValue = "John Doe") String passengerName) {

        Flight flight = flightRepository.findFlight(departureCity, arrivalCity)
                .orElseThrow(() -> new EntityNotFoundException("Flight is not found"));

        return ticketIntegration.buyTickets(List.of(new TicketRequest(passengerName, flight)))
                .stream()
                .map(TicketResponse::toString)
                .collect(joining("\n"));
    }
}
