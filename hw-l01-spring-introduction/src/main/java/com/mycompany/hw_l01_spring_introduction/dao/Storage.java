package com.mycompany.hw_l01_spring_introduction.dao;

import com.mycompany.hw_l01_spring_introduction.domain.CorrectAnswer;
import com.mycompany.hw_l01_spring_introduction.domain.Options;
import com.mycompany.hw_l01_spring_introduction.domain.Question;

import java.util.List;

public interface Storage {

    List<Question> getQuestions();

    List<Options> getOptions();

    List<CorrectAnswer> getCorrectAnswers();

}
