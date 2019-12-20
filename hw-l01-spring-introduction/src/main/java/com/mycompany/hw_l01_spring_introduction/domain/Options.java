package com.mycompany.hw_l01_spring_introduction.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Options {

    private final int questionId;
    private final List<String> values;

}
