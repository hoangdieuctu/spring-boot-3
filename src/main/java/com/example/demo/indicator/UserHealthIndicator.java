package com.example.demo.indicator;

import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class UserHealthIndicator implements HealthIndicator {

    private static final Logger logger = LoggerFactory.getLogger(UserHealthIndicator.class);

    private final UserRepository userRepository;

    public UserHealthIndicator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Health health() {
        logger.info("Checking health...");

        try {
            userRepository.count();
            return Health.up().build();
        } catch (Exception e) {
            return Health.down(e).build();
        }
    }
}
