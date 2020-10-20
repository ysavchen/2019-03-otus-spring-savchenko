package com.mycompany.hw_l28_spring_integration.config;

import lombok.RequiredArgsConstructor;
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
public class TicketProcessingConfig {

    @Bean
    public DirectChannel regRequestChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    public PublishSubscribeChannel responseTicketChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean
    public PublishSubscribeChannel domesticFlightsChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean
    public PublishSubscribeChannel internationalFlightsChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean
    public IntegrationFlow ticketFlow() {
        return IntegrationFlows.from("requestTicketChannel")
                .split()
//                .handle("ticketInfoService", "ticketsAvailable")
                .route("payload.flight.type", type -> type
                        .subFlowMapping("domestic", sf -> sf.channel("domesticFlightsChannel"))
                        .subFlowMapping("international", sf -> sf.channel("internationalFlightsChannel")))
                .get();
    }

    @Bean
    public IntegrationFlow domesticFlightsFlow() {
        return IntegrationFlows.from("domesticFlightsChannel")
                .handle("ticketSellingDomestic", "sellTicket")
                .channel("responseTicketChannel")
                .get();
    }

    @Bean
    public IntegrationFlow internationalFlightsFlow() {
        return IntegrationFlows.from("internationalFlightsChannel")
                .handle("ticketSellingInternational", "sellTicket")
                .channel("responseTicketChannel")
                .get();
    }
}
