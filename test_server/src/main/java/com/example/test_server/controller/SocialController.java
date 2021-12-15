package com.example.test_server.controller;

import com.example.test_server.pojo.Grace;
import com.example.test_server.pojo.Social;
import com.example.test_server.repository.SocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SocialController {

    @Autowired
    private SocialService socialService;

    @CrossOrigin
    @RequestMapping(value = "/social", method = RequestMethod.GET)
    public ResponseEntity<?> getSocial() {
        try{List<Social> social = socialService.getSocial();
            return ResponseEntity.ok(social);
        }catch(Exception e){
            throw e;
        }

    }
    @CrossOrigin
    @RequestMapping(value = "/social/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteSocial(@PathVariable("id") String id) {
        Map<String, Object> sendBack = new HashMap<>(); // ส่งค่ากลับไปที่ Client
        try{
            Boolean result = socialService.deleteSocial(id);
            if (result == false){
                sendBack.put("status", false);
                sendBack.put("message", "ส่งข้อมูลไม่สำเร็จ, โปรดลองอีกครั้ง");
            }else{
                sendBack.put("status", true);
                sendBack.put("message", "ดำเนินการลบโพสต์นี้แล้ว");
            }
        }catch(Exception e){
            throw e;
        }
        return ResponseEntity.ok(sendBack);
    }
    @CrossOrigin
    @RequestMapping(value = "/social", method = RequestMethod.POST)
    public ResponseEntity<?> addSocial(@RequestBody HashMap<String, String> formData) {
        Map<String, Object> sendBack = new HashMap<>(); // ส่งค่ากลับไปที่ Client
        try{
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Social result = socialService.addSocial(new Social(null, formData.get("detail"), formData.get("img"), formData.get("uid"), timestamp+""));
            if (result == null) {
                sendBack.put("status", false);
                sendBack.put("message", "ส่งข้อมูลไม่สำเร็จ, โปรดลองอีกครั้ง");
            }else{
                sendBack.put("status", true);
                sendBack.put("message", "เผยแพร่แล้ว");
                sendBack.put("id", result.get_id());
            }
        }catch(Exception e){
            throw e;
        }
        return ResponseEntity.ok(sendBack);


    }

    @CrossOrigin
    @RequestMapping(value = "/social", method = RequestMethod.PUT)
    public ResponseEntity<?> updateSocial(@RequestBody HashMap<String, String> formData) {
        Map<String, Object> sendBack = new HashMap<>(); // ส่งค่ากลับไปที่ Client
        try{
            Social target = socialService.getSocial(formData.get("sid"));
            Social result = socialService.updateSocial(new Social(target.get_id(), formData.get("detail"), target.getSocial_img(), target.getMember_id(), target.getSocial_timestamp()));
            if (result == null) {
                sendBack.put("status", false);
                sendBack.put("message", "ส่งข้อมูลไม่สำเร็จ, โปรดลองอีกครั้ง");
            }else{
                sendBack.put("status", true);
                sendBack.put("message", "อัปเดตโพสต์แล้ว");
            }
        }catch(Exception e){
            throw e;
        }
        return ResponseEntity.ok(sendBack);

    }


    @CrossOrigin
    @RequestMapping(value = "/social/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getSocial(@PathVariable("id") String id) {
        Social social = socialService.getSocial(id);
        try {
            if (social == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ไม่พบข้อมูลโพสต์");
            } else {
                return ResponseEntity.ok(social);
            }

        } catch (Exception e) {
            throw e;
        }


    }
}
