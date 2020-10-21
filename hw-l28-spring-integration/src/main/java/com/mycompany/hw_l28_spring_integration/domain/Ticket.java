package com.mycompany.hw_l28_spring_integration.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "tickets")
@NamedEntityGraph(name = "ticket-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("flight")
        })
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ticket_no", nullable = false)
    private UUID ticketNo = UUID.randomUUID();

    @Column(name = "passenger_name")
    private String passengerName;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "flight_id")
    private Flight flight;

    public Ticket() {
    }

    public Ticket(String passengerName, Flight flight) {
        this.passengerName = passengerName;
        this.flight = flight;
    }
}
