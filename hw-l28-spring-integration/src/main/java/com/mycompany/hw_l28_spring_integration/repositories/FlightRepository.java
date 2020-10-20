package com.mycompany.hw_l28_spring_integration.repositories;

import com.mycompany.hw_l28_spring_integration.domain.Flight;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, String> {

    @EntityGraph("flight-entity-graph")
    @Query("select f " +
            "from Flight f " +
            "where f.departureAirport.city = :departureCity " +
            "  and f.arrivalAirport.city = :arrivalCity")
    Optional<Flight> findFlight(String departureCity, String arrivalCity);
}
