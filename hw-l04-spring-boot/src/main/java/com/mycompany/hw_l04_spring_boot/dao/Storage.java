package com.mycompany.hw_l04_spring_boot.dao;

import com.mycompany.hw_l04_spring_boot.domain.Question;

import java.util.List;

public interface Storage {

    List<Question> getQuestions();

}
