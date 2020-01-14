package com.mycompany.hw_l05_advanced_spring_config;

import com.mycompany.hw_l05_advanced_spring_config.dao.Storage;
import com.mycompany.hw_l05_advanced_spring_config.domain.Answer;
import com.mycompany.hw_l05_advanced_spring_config.domain.Question;
import com.mycompany.hw_l05_advanced_spring_config.exceptions.QuestionMismatchException;
import com.mycompany.hw_l05_advanced_spring_config.service.ResultAnalyzerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ResultAnalyzerServiceImplTests {

    @MockBean
    private Storage storage;

    @MockBean
    private Question question;

    @Autowired
    private ResultAnalyzerService resultAnalyzerService;

    @Test
    void checkCorrectAnswer() {
        var correctAnswer = new Answer(1, "correct");
        when(question.getCorrectAnswer()).thenReturn(correctAnswer);
        when(storage.getQuestions()).thenReturn(List.of(question));

        var answer = new Answer(1, "correct");
        assertTrue(resultAnalyzerService.checkAnswer(answer),
                "Erroneous check of a correct answer: " + answer);
        assertEquals(1, resultAnalyzerService.getNumCorrectAnswers(),
                "Erroneous calculation of correct answers");
    }

    @Test
    void checkIncorrectAnswer() {
        var correctAnswer = new Answer(1, "correct");
        when(question.getCorrectAnswer()).thenReturn(correctAnswer);
        when(storage.getQuestions()).thenReturn(List.of(question));

        var answer = new Answer(1, "incorrect");
        assertFalse(resultAnalyzerService.checkAnswer(answer),
                "Erroneous check of an incorrect answer: " + answer);
        assertEquals(0, resultAnalyzerService.getNumCorrectAnswers(),
                "Erroneous calculation of incorrect answers");
    }

    @Test
    void checkAnswerCaseIgnored() {
        var correctAnswer = new Answer(1, "CORRECT");
        when(question.getCorrectAnswer()).thenReturn(correctAnswer);
        when(storage.getQuestions()).thenReturn(List.of(question));

        var answer = new Answer(1, "correct");
        assertTrue(resultAnalyzerService.checkAnswer(answer),
                "Erroneous check of answers with different case");
        assertEquals(1, resultAnalyzerService.getNumCorrectAnswers(),
                "Erroneous calculation of answers with different case");
    }

    @Test
    void checkQuestionMismatch() {
        var correctAnswer = new Answer(1, "mismatch");
        when(question.getCorrectAnswer()).thenReturn(correctAnswer);
        when(storage.getQuestions()).thenReturn(List.of(question));

        var answer = new Answer(2, "mismatch");
        assertThrows(QuestionMismatchException.class,
                () -> resultAnalyzerService.checkAnswer(answer),
                "Invalid logic for a question/ answer mismatch");
    }
}
