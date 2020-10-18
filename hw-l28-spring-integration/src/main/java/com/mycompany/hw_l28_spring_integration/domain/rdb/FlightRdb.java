package com.mycompany.hw_l28_spring_integration.domain.rdb;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "flights")
@NoArgsConstructor
public class FlightRdb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "flight_no", nullable = false)
    private UUID flightNo = UUID.randomUUID();

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "departure_airport_id")
    private AirportRdb departureAirport;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "arrival_airport_id")
    private AirportRdb arrivalAirport;

}
