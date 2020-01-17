package com.mycompany.hw_l04_spring_boot.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Answer {

    private final int questionId;
    private final String text;

}
