package com.mycompany.hw_l02_spring_config.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class User {

    private final String name;
    private final String surname;

}
