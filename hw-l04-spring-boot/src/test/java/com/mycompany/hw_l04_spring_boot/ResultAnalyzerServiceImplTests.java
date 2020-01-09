package com.mycompany.hw_l04_spring_boot;

import com.mycompany.hw_l04_spring_boot.dao.Storage;
import com.mycompany.hw_l04_spring_boot.domain.Answer;
import com.mycompany.hw_l04_spring_boot.domain.Question;
import com.mycompany.hw_l04_spring_boot.exceptions.QuestionMismatchException;
import com.mycompany.hw_l04_spring_boot.service.ResultAnalyzerService;
import com.mycompany.hw_l04_spring_boot.service.ResultAnalyzerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
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
