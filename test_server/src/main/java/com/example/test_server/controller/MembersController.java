package com.example.test_server.controller;

import com.example.test_server.pojo.Members;
import com.example.test_server.repository.LoginService;
import com.example.test_server.repository.MembersService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MembersController {
    @Autowired
    private MembersService membersService;
    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(
            @RequestParam("user") String user,
            @RequestParam("pass") String pass
    ) {
        System.out.println("user "+user);
        System.out.println("pass "+pass);
        try {
            Members login = loginService.login(user, pass);
            if (login == null) {
                return ResponseEntity.ok("user หรือ password ผิด");
            }else {
                return ResponseEntity.ok("login สำเร็จ");
            }
        } catch (Exception e){
            throw e;
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<?> getMembers() {
        List<Members> members = membersService.getMembers();
        return ResponseEntity.ok(members);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getMember(@PathVariable("id") String id) {
        Members member = membersService.getMember(id);
        if (member == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ไม่พบข้อมูล");
        } else {
            return ResponseEntity.ok(member);
        }
    }
}
