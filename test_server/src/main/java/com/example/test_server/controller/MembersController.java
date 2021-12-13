package com.example.test_server.controller;

import com.example.test_server.pojo.Members;
import com.example.test_server.repository.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MembersController {
    @Autowired
    private MembersService membersService;

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
