package com.example.circuitbreaker;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.net.Socket;

@Component
public class GraceNotesHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        try{
            new Socket(new java.net.URL("http://localhost:5000/login").getHost(), 5000);
        }
        catch(Exception e){
            return Health.down()
                    .withDetail("error", e.getMessage())
                    .build();
        }
        return Health.up().build();
    }
}
