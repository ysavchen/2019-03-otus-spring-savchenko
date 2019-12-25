package com.mycompany.hw_l02_spring_config.dao;

import com.mycompany.hw_l02_spring_config.domain.Question;

import java.util.List;

public interface Storage {

    List<Question> getQuestions();

}
