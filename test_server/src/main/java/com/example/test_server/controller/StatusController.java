package com.example.test_server.controller;

import com.example.test_server.pojo.Status;
import com.example.test_server.repository.StatusService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class StatusController {
    @Autowired
    private StatusService statusService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @CrossOrigin
    @RequestMapping(value = "/status/social/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getStatusFromSocialId(@PathVariable("id") String id){
        try {
//            ArrayList<Status> status = statusService.getStatusFromSocialId(id);
            Object Cast = rabbitTemplate.convertSendAndReceive("DircetGraceNotes", "getStatusFromSocialId", id);
            ArrayList<Status> status = (ArrayList<Status>) Cast;
            return ResponseEntity.ok(status);
        }catch(Exception e){
            throw e;
        }
    }
    @CrossOrigin
    @RequestMapping(value = "/status", method = RequestMethod.POST)
    public ResponseEntity<?> createStatus(@RequestBody Map<String, String> formData){
        Map<String, Object> sendBack = new HashMap<>(); // ส่งค่ากลับไปที่ Client
        try{
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Status newStatus = statusService.createStatus(new Status(null, formData.get("type"), formData.get("uid"), formData.get("sid"), ""+timestamp));
            if (newStatus == null){
                sendBack.put("message", "ส่งความคิดเห็นไม่สำเร็จ, โปรดลองอีกครั้ง");
                sendBack.put("status", false);
            }else {
                sendBack.put("message", "แสดงสถานะสำเร็จ");
                sendBack.put("status", true);
            }
        }catch(Exception e){
            throw e;
        }
        return  ResponseEntity.ok(sendBack);
    }

    @CrossOrigin
    @RequestMapping(value = "/status", method =RequestMethod.DELETE)
    public ResponseEntity<?> deleteStatus(@RequestParam("sid") String sid,
                                          @RequestParam("uid") String uid){
        try{
            return ResponseEntity.ok(statusService.deleteStatus(sid, uid));
        }catch(Exception e){
            throw e;
        }
    }
}
