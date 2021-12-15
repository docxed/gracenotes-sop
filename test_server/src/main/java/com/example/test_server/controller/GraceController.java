package com.example.test_server.controller;

import com.example.test_server.pojo.Grace;
import com.example.test_server.repository.GraceService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GraceController {

    @Autowired
    private GraceService graceService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @CrossOrigin //JOIN//
    @RequestMapping(value = "/grace", method = RequestMethod.GET) //SELECT * FROM grace JOIN members USING (member_id) ORDER BY grace_id ASC;//
    public ResponseEntity<?> getGrace() {
        try{
            Object Cast = rabbitTemplate.convertSendAndReceive("DircetGraceNotes", "getGrace", "");
            List<Grace> grace = (List<Grace>) Cast;
            return ResponseEntity.ok(grace);

        }catch(Exception e){
            throw e;
        }

    }

    @CrossOrigin//JOIN//
    @RequestMapping(value = "/grace/{id}", method = RequestMethod.GET) //SELECT * FROM grace JOIN members USING (member_id) WHERE grace_id = ?;//
    public ResponseEntity<?> getGrace(@PathVariable("id") String id) {
        try {
            Grace grace = graceService.getGrace(id);
            if (grace == null) { // กรณีรหัสผ่านผิด ไม่มี user นี้ในระบบ (ค่า null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ไม่พบข้อมูล");
            } else {
                return ResponseEntity.ok(grace);
            }

        } catch (Exception e) {
            throw e;
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/grace", method = RequestMethod.POST)
    public ResponseEntity<?> addGrace(@RequestBody Map<String, String> formData) {
        Map<String, Object> sendBack = new HashMap<>(); // ส่งค่ากลับไปที่ Client
        try{
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Grace grace = graceService.addGrace(new Grace(null, formData.get("time"), formData.get("date"), formData.get("detail"), formData.get("agency"), formData.get("image"), formData.get("check"), formData.get("uid"), formData.get("member_fname"), formData.get("member_lname"), ""+timestamp));
            if (grace == null) { // กรณีรหัสผ่านผิด ไม่มี user นี้ในระบบ (ค่า null)
                sendBack.put("message", "บันทึกความดีไม่สำเร็จ, โปรดลองอีกครั้ง");
                sendBack.put("status", false);
            }else {
                sendBack.put("message", "บันทึกความดีสำเร็จ");
                sendBack.put("status", true);
            }
        }catch(Exception e){
            throw e;
        }
        return ResponseEntity.ok(sendBack);
    }

    @CrossOrigin
    @RequestMapping(value = "/grace", method = RequestMethod.PUT)
    public ResponseEntity<?> updateGrace(@RequestBody Map<String, String> formData) {
        Map<String, Object> sendBack = new HashMap<>(); // ส่งค่ากลับไปที่ Client
        try{
            Grace target = graceService.getGrace(formData.get("sid"));
            Grace NewGrace = new Grace(target.get_id(), target.getGrace_time(), target.getGrace_date(), target.getGrace_detail(), target.getGrace_agency(), target.getGrace_img(), formData.get("value"), target.getMember_id(), target.getMember_fname(), target.getMember_lname(), target.getGrace_timestamp());
            Grace result = graceService.updateGrace(NewGrace);
            if (result == null) { // กรณีรหัสผ่านผิด ไม่มี user นี้ในระบบ (ค่า null)
                sendBack.put("message", "ส่งไม่สำเร็จ, โปรดลองอีกครั้ง");
                sendBack.put("status", false);
            }else {
                sendBack.put("message", "อัปเดตสถานะการตรวจเป็น " + result.getGrace_check() + " แล้ว");
                sendBack.put("status", true);
            }
        }catch(Exception e){
            throw e;
        }
        return ResponseEntity.ok(sendBack);
    }

    @CrossOrigin
    @RequestMapping(value = "/grace/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteGrace(@PathVariable("id") String id) {
        Map<String, Object> sendBack = new HashMap<>(); // ส่งค่ากลับไปที่ Client
        try{
            boolean result = graceService.deleteGrace(id);
            if (result == false) { // กรณีรหัสผ่านผิด ไม่มี user นี้ในระบบ (ค่า null)
                sendBack.put("message", "ส่งไม่สำเร็จ, โปรดลองอีกครั้ง");
                sendBack.put("status", false);
            }else {
                sendBack.put("message", "ลบบันทึกนี้แล้ว");
                sendBack.put("status", true);
            }
        }catch(Exception e){
            throw e;
        }
        return ResponseEntity.ok(sendBack);

    }
}
