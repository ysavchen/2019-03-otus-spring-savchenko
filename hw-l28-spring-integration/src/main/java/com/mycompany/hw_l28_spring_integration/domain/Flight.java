package com.mycompany.hw_l28_spring_integration.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "flights")
@NamedEntityGraph(name = "flight-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("departureAirport"),
                @NamedAttributeNode("arrivalAirport")
        })
@NoArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "flight_no", nullable = false)
    private UUID flightNo = UUID.randomUUID();

    @Column(name = "type", nullable = false)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "departure_airport_id")
    private Airport departureAirport;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "arrival_airport_id")
    private Airport arrivalAirport;

}
