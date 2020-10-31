package com.mycompany.hw_l29_spring_boot_actuator.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class JvmHeapSizeHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        long freeMemoryMB = Runtime.getRuntime().freeMemory() / (1024 * 1024);

        if (freeMemoryMB < 250L) {
            return Health.down()
                    .withDetail("message", "Low free heap memory: " + freeMemoryMB + " MB")
                    .build();
        }
        return Health.up()
                .withDetail("message", "Enough free heap memory: " + freeMemoryMB + " MB")
                .build();
    }
}