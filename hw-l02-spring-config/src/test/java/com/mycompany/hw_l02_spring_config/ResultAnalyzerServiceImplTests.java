package com.mycompany.hw_l02_spring_config;

import com.mycompany.hw_l02_spring_config.dao.Storage;
import com.mycompany.hw_l02_spring_config.domain.Answer;
import com.mycompany.hw_l02_spring_config.domain.Question;
import com.mycompany.hw_l02_spring_config.exceptions.QuestionMismatchException;
import com.mycompany.hw_l02_spring_config.service.ResultAnalyzerService;
import com.mycompany.hw_l02_spring_config.service.ResultAnalyzerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ResultAnalyzerServiceImplTests {

    @Mock
    private Storage storage;

    @Mock
    private Question question;

    private ResultAnalyzerService resultAnalyzerService;

    @BeforeEach
    void setUp() {
        resultAnalyzerService = new ResultAnalyzerServiceImpl(storage);
    }

    @Test
    void checkCorrectAnswer() {
        var correctAnswer = new Answer(1, "correct");
        when(question.getCorrectAnswer()).thenReturn(correctAnswer);
        when(storage.getQuestions()).thenReturn(List.of(question));

        var answer = new Answer(1, "correct");
        resultAnalyzerService.checkAnswer(answer);
        assertEquals(1, resultAnalyzerService.getNumCorrectAnswers());
    }

    @Test
    void checkIncorrectAnswer() {
        var correctAnswer = new Answer(1, "correct");
        when(question.getCorrectAnswer()).thenReturn(correctAnswer);
        when(storage.getQuestions()).thenReturn(List.of(question));

        var answer = new Answer(1, "incorrect");
        resultAnalyzerService.checkAnswer(answer);
        assertEquals(0, resultAnalyzerService.getNumCorrectAnswers());
    }

    @Test
    void checkAnswerCaseIgnored() {
        var correctAnswer = new Answer(1, "CORRECT");
        when(question.getCorrectAnswer()).thenReturn(correctAnswer);
        when(storage.getQuestions()).thenReturn(List.of(question));

        var answer = new Answer(1, "correct");
        resultAnalyzerService.checkAnswer(answer);
        assertEquals(1, resultAnalyzerService.getNumCorrectAnswers());
    }

    @Test
    void checkQuestionMismatch() {
        var correctAnswer = new Answer(1, "mismatch");
        when(question.getCorrectAnswer()).thenReturn(correctAnswer);
        when(storage.getQuestions()).thenReturn(List.of(question));

        var answer = new Answer(2, "mismatch");
        assertThrows(QuestionMismatchException.class,
                () -> resultAnalyzerService.checkAnswer(answer));
    }
}
