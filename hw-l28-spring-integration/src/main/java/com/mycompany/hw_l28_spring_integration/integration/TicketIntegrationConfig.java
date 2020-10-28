package com.mycompany.hw_l28_spring_integration.integration;

import com.mycompany.hw_l28_spring_integration.service.TicketInfoService;
import com.mycompany.hw_l28_spring_integration.service.TicketOperationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;

@Configuration
@EnableIntegration
@RequiredArgsConstructor
public class TicketIntegrationConfig {

    private static final String DOMESTIC_FLIGHTS = "domestic";
    private static final String INTERNATIONAL_FLIGHTS = "international";

    private static final String TICKET_AVAILABLE_METHOD = "ticketAvailable";
    private static final String SELL_TICKET_METHOD = "sellTicket";
    private static final String CANCEL_TICKET_METHOD = "cancelTicket";

    private final TicketInfoService ticketInfoService;

    @Qualifier("ticketOperationsDomestic")
    private final TicketOperationsService ticketOperationsDomestic;

    @Qualifier("ticketOperationsInternational")
    private final TicketOperationsService ticketOperationsInternational;

    @Bean
    public DirectChannel buyTicketChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    public DirectChannel cancelTicketChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    public PublishSubscribeChannel responseTicketChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean
    public PublishSubscribeChannel domesticOperationsChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean
    public PublishSubscribeChannel internationalOperationsChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean
    public IntegrationFlow buyTicketFlow() {
        return IntegrationFlows.from("buyTicketChannel")
                .handle(ticketInfoService, TICKET_AVAILABLE_METHOD)
                .route("payload.flight.type", flightType -> flightType
                        .subFlowMapping(DOMESTIC_FLIGHTS, sf -> sf.handle(ticketOperationsDomestic, SELL_TICKET_METHOD))
                        .subFlowMapping(INTERNATIONAL_FLIGHTS, sf -> sf.handle(ticketOperationsInternational, SELL_TICKET_METHOD))
                )
                .channel("responseTicketChannel")
                .get();
    }

    @Bean
    public IntegrationFlow cancelTicketFlow() {
        return IntegrationFlows.from("cancelTicketChannel")
                .route("payload.flight.type", flightType -> flightType
                        .subFlowMapping(DOMESTIC_FLIGHTS, sf -> sf.handle(ticketOperationsDomestic, CANCEL_TICKET_METHOD))
                        .subFlowMapping(INTERNATIONAL_FLIGHTS, sf -> sf.handle(ticketOperationsInternational, CANCEL_TICKET_METHOD))
                )
                .channel("responseTicketChannel")
                .get();
    }
}
