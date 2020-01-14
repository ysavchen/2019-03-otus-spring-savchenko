package com.mycompany.hw_l05_advanced_spring_config.service;

import com.mycompany.hw_l05_advanced_spring_config.domain.User;

public interface TestingService {

    void setUser(User user);

    String next();

    String acceptAnswer(String answer);
}
