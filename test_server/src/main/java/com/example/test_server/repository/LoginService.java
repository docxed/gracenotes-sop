package com.example.test_server.repository;

import com.example.test_server.pojo.Members;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LoginService {
    @Autowired
    private LoginRepository repository;

    public LoginService(LoginRepository repository) {
        this.repository = repository;
    }

    public Members login(Map<String, String> formData) {
        return repository.login(formData.get("user"), formData.get("pass"));
    }


}
