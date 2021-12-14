package com.example.test_server.controller;

import com.example.test_server.pojo.Members;
import com.example.test_server.repository.LoginService;
import com.example.test_server.repository.MembersService;
import org.bson.json.JsonObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MembersController {
    @Autowired
    private MembersService membersService;
    @Autowired
    private LoginService loginService;

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody Map<String, String> formData) {
        try {
            Members login = loginService.login(formData.get("user"), formData.get("pass")); // ส่งค่าไปที่ Service
            Map<String, Object> sendBack = new HashMap<>(); // ส่งค่ากลับไปที่ Client
            if (login == null) { // กรณีรหัสผ่านผิด ไม่มี user นี้ในระบบ (ค่า null)
                sendBack.put("message", "รหัสนักเรียน หรือ รหัสผ่านไม่ถูกต้อง, โปรดลองอีกครั้ง");
                sendBack.put("status", false);
                return ResponseEntity.ok(sendBack);
            }else {
                sendBack.put("message", "Login สำเร็จ");
                sendBack.put("status", true);
                sendBack.put("ses_id", login.get_id());
                sendBack.put("ses_user", login.getMember_user());
                sendBack.put("ses_level", login.getMember_level());
                return ResponseEntity.ok(sendBack);
            }
        } catch (Exception e){
            throw e;
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<?> getMembers() {
        List<Members> members = membersService.getMembers();
        return ResponseEntity.ok(members);
    }

    @CrossOrigin
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
