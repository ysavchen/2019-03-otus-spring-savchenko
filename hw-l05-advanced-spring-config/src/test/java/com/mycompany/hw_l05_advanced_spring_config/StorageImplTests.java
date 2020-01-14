package com.mycompany.hw_l05_advanced_spring_config;

import com.mycompany.hw_l05_advanced_spring_config.app.AppLocale;
import com.mycompany.hw_l05_advanced_spring_config.dao.Storage;
import com.mycompany.hw_l05_advanced_spring_config.domain.Answer;
import com.mycompany.hw_l05_advanced_spring_config.domain.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;

import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
public class StorageImplTests {

    @MockBean
    private AppLocale appLocale;

    @Autowired
    private Storage storage;

    @Test
    void getQuestionsTest() {
        when(appLocale.getLocale()).thenReturn(Locale.US);
        List<Question> questions = storage.getQuestions();

        var question = new Question(
                1, "question one?",
                List.of("1", "15", "6", "37"), new Answer(1, "15")
        );
        assertThat(questions).contains(question);
    }
}
