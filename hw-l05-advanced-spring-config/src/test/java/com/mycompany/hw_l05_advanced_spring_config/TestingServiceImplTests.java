package com.mycompany.hw_l05_advanced_spring_config;

import com.mycompany.hw_l05_advanced_spring_config.domain.Answer;
import com.mycompany.hw_l05_advanced_spring_config.domain.Question;
import com.mycompany.hw_l05_advanced_spring_config.service.MessageSourceService;
import com.mycompany.hw_l05_advanced_spring_config.service.TestingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TestingServiceImplTests {

    @MockBean
    private MessageSourceService msService;

    @Autowired
    private TestingService testingService;

    private Question question1;

    @BeforeEach
    void setUp() {
        question1 = new Question(
                1, "question one?",
                List.of("1", "15", "6", "37"), new Answer(1, "15")
        );
    }

    @Test
    void nextQuestion() {
        String nextQuestion = testingService.next();
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
}
