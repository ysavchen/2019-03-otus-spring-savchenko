package com.mycompany.hw_l04_spring_boot.service;

import com.mycompany.hw_l04_spring_boot.app.AppLocale;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageSourceServiceImpl implements MessageSourceService {

    private final AppLocale appLocale;
    private final MessageSource messageSource;

    @Override
    public String getMessage(String key, Object... args) {
        return messageSource.getMessage(key, args, appLocale.getLocale());
    }
}
