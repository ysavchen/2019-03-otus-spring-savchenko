package com.mycompany.hw_l05_advanced_spring_config.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Answer {

    private final int questionId;
    private final String text;

}
