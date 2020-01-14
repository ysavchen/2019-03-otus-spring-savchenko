package com.mycompany.hw_l05_advanced_spring_config;

import com.mycompany.hw_l05_advanced_spring_config.dao.Storage;
import com.mycompany.hw_l05_advanced_spring_config.domain.Answer;
import com.mycompany.hw_l05_advanced_spring_config.domain.Question;
import com.mycompany.hw_l05_advanced_spring_config.service.MessageSourceService;
import com.mycompany.hw_l05_advanced_spring_config.service.TestingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TestingServiceImplTests {

    @MockBean
    private Storage storage;

    @MockBean
    private MessageSourceService msService;

    @Autowired
    private TestingService testingService;

    private Question question1;
    private Question question2;

    @BeforeEach
    void setUp() {
        question1 = new Question(
                1, "question one?",
                List.of("1", "15", "6", "37"), new Answer(1, "15")
        );
        question2 = new Question(
                2, "question two?",
                List.of("answerOne", "answerOne"), new Answer(2, "answerOne")
        );
        when(storage.getQuestions()).thenReturn(List.of(question1, question2));
    }

    @Test
    void nextQuestion() {
        String nextQuestion = testingService.next();
        System.out.println(nextQuestion);
        assertEquals(question1.getText() + "\n" + question1.getOptions(), nextQuestion);
    }

    @Test
    void nextResults() {
        when(msService.getMessage(anyString(), any())).thenReturn("data");
        testingService.next();
        testingService.next();
        String results = testingService.next();
        String expResults = "data\ndata\ndata\ndata";
        assertEquals(expResults, results);
    }

    @Test
    void acceptCorrectAnswer() {
        assertEquals("Answer is correct", testingService.acceptAnswer("15"));
    }

    @Test
    void acceptIncorrectAnswer() {
        assertEquals("Answer is incorrect. The right option is " + question1.getCorrectAnswer().getText(),
                testingService.acceptAnswer("20"));
    }
}
