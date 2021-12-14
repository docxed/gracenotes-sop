package com.example.test_server.repository;

import com.example.test_server.pojo.Members;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private LoginRepository repository;

    public LoginService(LoginRepository repository) {
        this.repository = repository;
    }

    public Members login(String user, String pass) {
        return repository.login(user, pass);
    }


}
