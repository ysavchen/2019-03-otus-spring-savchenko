package com.mycompany.hw_l28_spring_integration.domain.rdb;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "tickets")
@NoArgsConstructor
public class TicketRdb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ticket_no", nullable = false)
    private UUID ticketNo = UUID.randomUUID();

    @Column(name = "passenger_name")
    private String passengerName;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "flight_id")
    private FlightRdb flight;

}
