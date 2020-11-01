package com.mycompany.hw_l34_srping_cloud.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter @Setter
@ConfigurationProperties("resilience4j.ratelimiter.instances.book-service")
public class RateLimiterProps {

    private int limitForPeriod;

}
