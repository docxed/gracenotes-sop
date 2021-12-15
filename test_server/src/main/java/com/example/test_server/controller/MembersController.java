package com.example.test_server.controller;

import com.example.test_server.pojo.Members;
import com.example.test_server.repository.LoginService;
import com.example.test_server.repository.MembersService;
import com.example.test_server.repository.RegisterService;
import org.bson.json.JsonObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MembersController {
    @Autowired
    private MembersService membersService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private RegisterService registerService;

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody Map<String, String> formData) {
        Map<String, Object> sendBack = new HashMap<>(); // ส่งค่ากลับไปที่ Client
        try {
            Members login = loginService.login(formData.get("user"), formData.get("pass")); // ส่งค่าไปที่ Service
            if (login == null) { // กรณีรหัสผ่านผิด ไม่มี user นี้ในระบบ (ค่า null)
                sendBack.put("message", "รหัสนักเรียน หรือ รหัสผ่านไม่ถูกต้อง, โปรดลองอีกครั้ง");
                sendBack.put("status", false);
            }else {
                sendBack.put("message", "Login สำเร็จ");
                sendBack.put("status", true);
                sendBack.put("ses_id", login.get_id());
                sendBack.put("ses_user", login.getMember_user());
                sendBack.put("ses_level", login.getMember_level());
            }
        } catch (Exception e){
            throw e;
        }
        return ResponseEntity.ok(sendBack);
    }

    @CrossOrigin
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody HashMap<String, String> formData) {
        Map<String, Object> sendBack = new HashMap<>(); // ส่งค่ากลับไปที่ Client
        try {
            boolean checkDupped = registerService.checkDupped(formData.get("user"));
            if (!checkDupped) { // รหัสนักเรียนนี้ถูกใช้แล้ว เพราะ True แปลว่าไม่มีใน db
                sendBack.put("status", false);
                sendBack.put("message", "รหัสนักเรียนนี้ถูกใช้แล้ว");
                return ResponseEntity.ok(sendBack);
            }
        } catch (Exception e) {
            throw e;
        }
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Members register = registerService.register(new Members(null, formData.get("user"), formData.get("pass"), formData.get("fname"), formData.get("lname"), formData.get("classes"), formData.get("no"), formData.get("dob"), formData.get("address"), formData.get("image"), "student", ""+timestamp));
            if (register == null) {
                sendBack.put("status", false);
                sendBack.put("message", "สมัครสมาชิกไม่สำเร็จ");
            }else{
                sendBack.put("status", true);
                sendBack.put("message", "สมัครสมาชิกสำเร็จ");
            }
        } catch (Exception e) {
            throw e;
        }
        return ResponseEntity.ok(sendBack);
    }

    @CrossOrigin
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody HashMap<String, String> formData) {
        Map<String, Object> sendBack = new HashMap<>(); // ส่งค่ากลับไปที่ Client
        try {
            Members update = membersService.update(new Members(formData.get("id"), formData.get("user"), formData.get("pass"), formData.get("fname"), formData.get("lname"), formData.get("classes"), formData.get("no"), formData.get("dob"), formData.get("address"), formData.get("image"), formData.get("level"), formData.get("timestamp")));
            if (update == null) {
                sendBack.put("status", false);
                sendBack.put("message", "อัปเดตข้อมูลแล้วไม่สำเร็จ");
            }else{
                sendBack.put("status", true);
                sendBack.put("message", "อัปเดตข้อมูลแล้ว");
            }
        } catch (Exception e) {
            throw e;
        }
        return ResponseEntity.ok(sendBack);
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

    @CrossOrigin
    @RequestMapping(value = "/user/level", method = RequestMethod.PUT)
    public ResponseEntity<?> updateLevel(@RequestBody HashMap<String, String> formData) {
        Map<String, Object> sendBack = new HashMap<>(); // ส่งค่ากลับไปที่ Client
        try {
            Members member = membersService.getMember(formData.get("sid"));
            Members result = membersService.updateLevel(new Members(member.get_id(), member.getMember_user(), member.getMember_password(), member.getMember_fname(), member.getMember_lname(), member.getMember_class(), member.getMember_no(), member.getMember_dob(), member.getMember_address(), member.getMember_img(), formData.get("level"), member.getMember_timestamp()));
            if (result == null) {
                sendBack.put("status", false);
                sendBack.put("message", "ส่งข้อมูลแล้วไม่สำเร็จ, โปรดลองอีกครั้ง");
            }else{
                sendBack.put("status", true);
                sendBack.put("message", "ตั้งบทบาทเป็น "+result.getMember_level()+" แล้ว");
            }
        } catch (Exception e){
            throw e;
        }
        return ResponseEntity.ok(sendBack);
    }

    @CrossOrigin
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteMember(@PathVariable("id") String id) {
        Map<String, Object> sendBack = new HashMap<>(); // ส่งค่ากลับไปที่ Client
        try {
            Boolean result = membersService.deleteMember(id);
            if (result == false) {
                sendBack.put("status", false);
                sendBack.put("message", "ส่งข้อมูลแล้วไม่สำเร็จ, โปรดลองอีกครั้ง");
            }else{
                sendBack.put("status", true);
                sendBack.put("message", "ดำเนินการลบแล้ว");
            }
        }catch (Exception e){
            throw e;
        }
        return ResponseEntity.ok(sendBack);
    }
}
