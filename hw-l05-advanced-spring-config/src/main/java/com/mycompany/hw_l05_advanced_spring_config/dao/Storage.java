package com.mycompany.hw_l05_advanced_spring_config.dao;

import com.mycompany.hw_l05_advanced_spring_config.domain.Question;

import java.util.List;

public interface Storage {

    List<Question> getQuestions();

}
