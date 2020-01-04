package com.mycompany.hw_l02_spring_config.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Component
public class AppLocale {

    private final String language;
    private final String country;
    private final MessageSource messageSource;

    private final Map<String, String> values = new HashMap<>();

    public AppLocale(@Value("${default.language}") String language,
                     @Value("${default.country}") String country,
                     MessageSource messageSource) {
        this.language = language;
        this.country = country;
        this.messageSource = messageSource;
        fillValues();
    }

    private void fillValues() {
        values.put("user.name", getMessage("user.name"));
        values.put("user.surname", getMessage("user.surname"));
        values.put("questionsPath", getMessage("questionsPath"));

        values.put("result.info", getMessage("result.info"));
        values.put("result.numQuestions", getMessage("result.numQuestions"));
        values.put("result.correctAnswers", getMessage("result.correctAnswers"));
        values.put("result.incorrectAnswers", getMessage("result.incorrectAnswers"));
    }

    private String getMessage(String key) {
        return messageSource.getMessage(key, null, new Locale(language, country));
    }

    public String getValue(String key) {
        return values.get(key);
    }
}
