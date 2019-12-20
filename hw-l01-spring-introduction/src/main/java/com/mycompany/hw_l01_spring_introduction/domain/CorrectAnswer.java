package com.mycompany.hw_l01_spring_introduction.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CorrectAnswer {

    private final int questionId;
    private final String text;

}
