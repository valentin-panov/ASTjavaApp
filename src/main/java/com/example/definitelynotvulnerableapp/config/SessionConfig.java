package com.example.definitelynotvulnerableapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.MapSession;
import org.springframework.session.MapSessionRepository;

import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class SessionConfig {
    @Bean
    public MapSessionRepository sessionRepository() {
        return new MapSessionRepository(new ConcurrentHashMap<>()) {
            @Override
            public MapSession createSession() {
                String id = String.valueOf(System.currentTimeMillis());
                MapSession result = new MapSession(id);
                result.setMaxInactiveInterval(Duration.ofSeconds(6000));
                return result;
            }
        };
    }
}
