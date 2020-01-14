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

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String next() {
        var questions = storage.getQuestions();
        if (nextQuestion < questions.size()) {
            var question = storage.getQuestions().get(nextQuestion);
            nextQuestion++;
            return question.getText() + "\n" + question.getOptions();
        }
        return getResults();
    }

    @Override
    public String acceptAnswer(String answer) {
        var question = storage.getQuestions().get(answerToQuestion);
        var isCorrect = resultAnalyzer.checkAnswer(new Answer(question.getId(), answer));
        answerToQuestion++;
        if (isCorrect) {
            return "Answer is correct";
        }
        return "Answer is incorrect. The right option is " + question.getCorrectAnswer().getText();
    }

    private String getResults() {
        int numQuestions = storage.getQuestions().size();
        int numCorrectAnswers = resultAnalyzer.getNumCorrectAnswers();

        if (user == null) {
            user = new User("Not defined", "Not defined");
        }
        return msService.getMessage("result.info", user.getName(), user.getSurname()) + "\n" +
                msService.getMessage("result.numQuestions", numQuestions) + "\n" +
                msService.getMessage("result.correctAnswers", numCorrectAnswers) + "\n" +
                msService.getMessage("result.incorrectAnswers", (numQuestions - numCorrectAnswers));
    }
}
