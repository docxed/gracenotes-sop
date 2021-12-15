package com.example.test_server.controller;

import com.example.test_server.pojo.ReportFeedback;
import com.example.test_server.repository.ReportFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ReportFeedbackController {
    @Autowired
    private ReportFeedbackService reportFeedbackService;

    @CrossOrigin
    @RequestMapping(value = "/reply", method = RequestMethod.GET)
    public ResponseEntity<?> getReply(){
        try {
            ArrayList<ReportFeedback> rpl = reportFeedbackService.getReportFeedBack();
            return ResponseEntity.ok(rpl);
        }catch(Exception e){
            return ResponseEntity.ok(e);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/reply/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteReply(@PathVariable("id") String id){
        Map<String, Object> sendBack = new HashMap<>(); // ส่งค่ากลับไปที่ Client
        try {
            Boolean status = reportFeedbackService.deleteReportFeedBack(id);
            if (status == false) {
                sendBack.put("status", false);
                sendBack.put("message", "ส่งข้อมูลไม่สำเร็จ, โปรดลองอีกครั้ง");
            }else {
                sendBack.put("status", true);
                sendBack.put("message", "ลบแล้ว");
            }
        }catch(Exception e){
            throw e;
        }
        return ResponseEntity.ok(sendBack);
    }

    @CrossOrigin
    @RequestMapping(value = "/reply", method = RequestMethod.POST)
    public ResponseEntity<?> createReply(@RequestBody HashMap<String, String> formData){
        Map<String, Object> sendBack = new HashMap<>(); // ส่งค่ากลับไปที่ Client
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            ReportFeedback newReply = reportFeedbackService.createReportFeedBack(
                    new ReportFeedback(null, formData.get("sid"), formData.get("detail"), formData.get("uid"), ""+timestamp));
            if (newReply == null) {
                sendBack.put("status", false);
                sendBack.put("message", "ส่งข้อมูลไม่สำเร็จ, โปรดลองอีกครั้ง");
            }else {
                sendBack.put("status", true);
                sendBack.put("message", "ส่งรายงานสำเร็จ");
            }
        }catch(Exception e){
            throw e;
        }
        return ResponseEntity.ok(sendBack);
    }
}
