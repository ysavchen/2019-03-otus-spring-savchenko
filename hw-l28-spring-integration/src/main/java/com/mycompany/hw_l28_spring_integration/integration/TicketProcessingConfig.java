package com.mycompany.hw_l28_spring_integration.integration;

import com.mycompany.hw_l28_spring_integration.domain.Ticket;
import com.mycompany.hw_l28_spring_integration.integration.TicketOperation.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.router.ExpressionEvaluatingRouter;

@Configuration
@EnableIntegration
@RequiredArgsConstructor
public class TicketProcessingConfig {

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
    public ExpressionEvaluatingRouter flightTypeRouter() {
        var router = new ExpressionEvaluatingRouter("payload.object.flight.type");
        router.setChannelMapping("domestic", "domesticOperationsChannel");
        router.setChannelMapping("international", "internationalOperationsChannel");
        return router;
    }

    @Bean
    public IntegrationFlow cancelTicketFlow() {
        return IntegrationFlows.from("cancelTicketChannel")
                .transform(Ticket.class,
                        ticket -> new TicketOperation<>(ticket, Type.Cancel)
                )
                .route(flightTypeRouter())
                .get();
    }

    @Bean
    public IntegrationFlow buyTicketFlow() {
        return IntegrationFlows.from("buyTicketChannel")
                .handle("ticketInfoService", "ticketAvailable")
                .transform(TicketRequest.class,
                        request -> new TicketOperation<>(request, Type.Sell)
                )
                .route(flightTypeRouter())
                .get();
    }

    @Bean
    public IntegrationFlow domesticOperationsFlow() {
        return IntegrationFlows.from("domesticOperationsChannel")
                .route("payload.type", operationType -> operationType
                        .subFlowMapping("Sell", sf -> sf.handle("ticketOperationsDomestic", "sellTicket"))
                        .subFlowMapping("Cancel", sf -> sf.handle("ticketOperationsDomestic", "cancelTicket"))
                )
                .channel("responseTicketChannel")
                .get();
    }

    @Bean
    public IntegrationFlow internationalOperationsFlow() {
        return IntegrationFlows.from("internationalOperationsChannel")
                .route("payload.type", operationType -> operationType
                        .subFlowMapping("Sell", sf -> sf.handle("ticketOperationsInternational", "sellTicket"))
                        .subFlowMapping("Cancel", sf -> sf.handle("ticketOperationsInternational", "cancelTicket"))
                )
                .channel("responseTicketChannel")
                .get();
    }
}
