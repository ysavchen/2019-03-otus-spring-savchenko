package com.mycompany.hw_l28_spring_integration.integration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TicketOperation<T> {

    private final T object;
    private final Type type;

    public enum Type {
        Sell, Cancel
    }
}
