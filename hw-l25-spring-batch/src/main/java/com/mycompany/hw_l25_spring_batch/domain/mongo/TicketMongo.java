package com.mycompany.hw_l25_spring_batch.domain.mongo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document(collection = "tickets")
@Accessors(chain = true)
public class TicketMongo {

    @Id
    private String id;
    private UUID ticketNo;
    private String passengerName;

    @DBRef
    private FlightMongo flight;

    public TicketMongo() {
    }

    public TicketMongo(UUID ticketNo, String passengerName, FlightMongo flight) {
        this.ticketNo = ticketNo;
        this.passengerName = passengerName;
        this.flight = flight;
    }
}
