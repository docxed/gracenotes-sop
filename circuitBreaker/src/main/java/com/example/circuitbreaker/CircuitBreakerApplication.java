package com.example.circuitbreaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class CircuitBreakerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CircuitBreakerApplication.class, args);
    }

    @Autowired
    CircuitBreakerFactory circuitBreakerFactory;

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(@RequestBody Map<String, String> formData){
        Map<String, Object> sendBack = new HashMap<>();
        sendBack.put("message", "หมดเวลาการเชื่อมต่อ หรือลองใหม่อีกครั้งในภายหลัง");
        sendBack.put("status", false);
        return circuitBreakerFactory.create("login").run(
                () -> new RestTemplate().postForObject("http://localhost:5000/login", formData, String.class),
                throwable -> ResponseEntity.ok(sendBack)
        );
    }
}
