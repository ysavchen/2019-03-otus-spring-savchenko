package com.mycompany.hw_l02_spring_config.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class AppLocale {

    private final Locale locale;

    public AppLocale(@Value("${default.language}") String language,
                     @Value("${default.country}") String country) {
        this.locale = new Locale(language, country);
    }

    public Locale getLocale() {
        return locale;
    }
}
