package com.mycompany.hw_l25_spring_batch.shell;

import com.mycompany.hw_l25_spring_batch.domain.mongo.FlightMongo;
import com.mycompany.hw_l25_spring_batch.domain.mongo.TicketMongo;
import com.mycompany.hw_l25_spring_batch.repositories.FlightMongoRepository;
import com.mycompany.hw_l25_spring_batch.repositories.TicketMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import static java.util.stream.Collectors.joining;

@ShellComponent
@RequiredArgsConstructor
public class MongoCommands {

    private final FlightMongoRepository flightRepository;
    private final TicketMongoRepository ticketRepository;

    @ShellMethod(value = "Find all flights", key = {"faf", "find-all-flights"})
    public String findAllFlights() {
        var flights = flightRepository.findAll();
        if (flights.isEmpty()) {
            return "No flights found";
        }

        return flights.stream()
                .map(FlightMongo::toString)
                .collect(joining("\n"));
    }

    @ShellMethod(value = "Find all tickets", key = {"fat", "find-all-tickets"})
    public String findAllTickets() {
        var tickets = ticketRepository.findAll();
        if (tickets.isEmpty()) {
            return "No tickets found";
        }

        return tickets.stream()
                .map(TicketMongo::toString)
                .collect(joining("\n"));
    }
}
