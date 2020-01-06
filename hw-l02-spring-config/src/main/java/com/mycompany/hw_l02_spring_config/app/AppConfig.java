package com.mycompany.hw_l02_spring_config.app;

import lombok.val;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@PropertySource("classpath:application.properties")
@ComponentScan("com.mycompany")
@Configuration
public class AppConfig {

    @Bean
    public MessageSource messageSource() {
        val ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/i18n/bundle");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }
}
