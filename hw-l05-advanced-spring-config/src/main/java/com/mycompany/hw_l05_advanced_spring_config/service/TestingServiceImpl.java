package com.mycompany.hw_l05_advanced_spring_config.service;

import com.mycompany.hw_l05_advanced_spring_config.dao.Storage;
import com.mycompany.hw_l05_advanced_spring_config.domain.Answer;
import com.mycompany.hw_l05_advanced_spring_config.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestingServiceImpl implements TestingService {

    private final Storage storage;
    private final ResultAnalyzerService resultAnalyzer;
    private final MessageSourceService msService;

    private User user;
    private int nextQuestion = 0;
    private int answerToQuestion = 0;

    public void setUser(User user) {
        this.user = user;
    }

    public String nextQuestion() {
        var questions = storage.getQuestions();
        if (nextQuestion < questions.size()) {
            var question = storage.getQuestions().get(nextQuestion);
            nextQuestion++;
            return question.getText() + "\n" + question.getOptions();
        }
        return "No more questions available. Enter 'results' to see test results";
    }

    public String acceptAnswer(String answer) {
        var question = storage.getQuestions().get(answerToQuestion);
        var isCorrect = resultAnalyzer.checkAnswer(new Answer(question.getId(), answer));
        answerToQuestion++;
        if (isCorrect) {
            return "Answer is correct";
        }
        return "Answer is incorrect. The right option is " + question.getCorrectAnswer().getText();
    }

    public String getResults() {
        int numQuestions = storage.getQuestions().size();
        int numCorrectAnswers = resultAnalyzer.getNumCorrectAnswers();
        return msService.getMessage("result.info", user.getName(), user.getSurname()) + "\n" +
                msService.getMessage("result.numQuestions", numQuestions) + "\n" +
                msService.getMessage("result.correctAnswers", numCorrectAnswers) + "\n" +
                msService.getMessage("result.incorrectAnswers", (numQuestions - numCorrectAnswers));
    }
}
