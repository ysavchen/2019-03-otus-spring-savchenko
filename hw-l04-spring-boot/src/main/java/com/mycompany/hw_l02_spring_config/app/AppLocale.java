package com.mycompany.hw_l02_spring_config.app;

import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class AppLocale {

    private final Locale locale;

    public AppLocale(AppProperties props) {
        this.locale = new Locale(props.getLanguage(), props.getCountry());
    }

    public Locale getLocale() {
        return locale;
    }
}
