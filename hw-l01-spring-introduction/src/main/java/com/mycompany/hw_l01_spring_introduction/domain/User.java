package com.mycompany.hw_l01_spring_introduction.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class User {

    private final String name;
    private final String surname;

}
