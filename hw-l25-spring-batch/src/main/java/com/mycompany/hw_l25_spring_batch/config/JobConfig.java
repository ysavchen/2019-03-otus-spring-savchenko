package com.mycompany.hw_l25_spring_batch.config;

import com.mycompany.hw_l25_spring_batch.domain.mongo.AirportMongo;
import com.mycompany.hw_l25_spring_batch.domain.mongo.FlightMongo;
import com.mycompany.hw_l25_spring_batch.domain.mongo.TicketMongo;
import com.mycompany.hw_l25_spring_batch.domain.rdb.FlightRdb;
import com.mycompany.hw_l25_spring_batch.domain.rdb.TicketRdb;
import com.mycompany.hw_l25_spring_batch.repositories.FlightMongoRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.database.HibernatePagingItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Configuration
@RequiredArgsConstructor
public class JobConfig {

    private static final int CHUNK_SIZE = 5;
    public static final String JOB_NAME = "loadDataToMongo";

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final FlightMongoRepository flightMongoRepository;
    private final EntityManagerFactory entityManagerFactory;
    private final MongoOperations mongoOperations;

    @Bean
    public Job loadDataToMongo() {
        return jobBuilderFactory.get(JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .start(loadFlightsToMongo())
                .next(loadTicketsToMongo())
                .build();
    }

    @Bean
    public Step loadFlightsToMongo() {
        return stepBuilderFactory.get("loadFlightsToMongo")
                .<FlightRdb, FlightMongo>chunk(CHUNK_SIZE)
                .reader(flightRdbReader())
                .processor(flightProcessor())
                .writer(flightMongoWriter())
                .build();
    }

    @Bean
    public Step loadTicketsToMongo() {
        return stepBuilderFactory.get("loadTicketsToMongo")
                .<TicketRdb, TicketMongo>chunk(CHUNK_SIZE)
                .reader(ticketRdbReader())
                .processor(ticketProcessor())
                .writer(ticketMongoWriter())
                .build();
    }

    @Bean
    public HibernatePagingItemReader<FlightRdb> flightRdbReader() {
        var reader = new HibernatePagingItemReader<FlightRdb>();
        reader.setSessionFactory(entityManagerFactory.unwrap(SessionFactory.class));
        reader.setPageSize(CHUNK_SIZE);
        reader.setQueryString(
                "select flightRdb " +
                        "from FlightRdb flightRdb " +
                        "left join fetch flightRdb.departureAirport " +
                        "left join fetch flightRdb.arrivalAirport"
        );
        return reader;
    }

    @Bean
    public ItemProcessor<FlightRdb, FlightMongo> flightProcessor() {
        return flightRdb -> {
            var departureAirport = new AirportMongo(
                    flightRdb.getDepartureAirport().getName(),
                    flightRdb.getDepartureAirport().getCity()
            );
            var arrivalAirport = new AirportMongo(
                    flightRdb.getArrivalAirport().getName(),
                    flightRdb.getArrivalAirport().getCity()
            );
            return new FlightMongo(flightRdb.getFlightNo(), departureAirport, arrivalAirport);
        };
    }

    @Bean
    public MongoItemWriter<FlightMongo> flightMongoWriter() {
        var writer = new MongoItemWriter<FlightMongo>();
        writer.setCollection("flights");
        writer.setTemplate(mongoOperations);
        return writer;
    }

    @Bean
    public HibernatePagingItemReader<TicketRdb> ticketRdbReader() {
        var reader = new HibernatePagingItemReader<TicketRdb>();
        reader.setSessionFactory(entityManagerFactory.unwrap(SessionFactory.class));
        reader.setPageSize(CHUNK_SIZE);
        reader.setQueryString(
                "select ticketRdb " +
                        "from TicketRdb ticketRdb " +
                        "left join fetch ticketRdb.flight"
        );
        return reader;
    }

    @Bean
    public ItemProcessor<TicketRdb, TicketMongo> ticketProcessor() {
        return ticketRdb -> {
            UUID flightNo = ticketRdb.getFlight().getFlightNo();
            FlightMongo flight = flightMongoRepository.findByFlightNo(flightNo)
                    .orElseThrow(() -> new EntityNotFoundException("Flight (flightNo = " + flightNo + ") is not found"));
            return new TicketMongo(ticketRdb.getTicketNo(), ticketRdb.getPassengerName(), flight);
        };
    }

    @Bean
    public MongoItemWriter<TicketMongo> ticketMongoWriter() {
        var writer = new MongoItemWriter<TicketMongo>();
        writer.setCollection("tickets");
        writer.setTemplate(mongoOperations);
        return writer;
    }
}
