package com.mycompany.hw_l02_spring_config.app;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@ConfigurationProperties("application")
public class AppProperties {

    private String language;
    private String country;

}
