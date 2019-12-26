package com.mycompany.hw_l02_spring_config.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class User {

    private final String name;
    private final String surname;

}
