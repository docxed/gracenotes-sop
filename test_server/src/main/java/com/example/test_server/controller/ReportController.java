package com.example.test_server.controller;

import com.example.test_server.pojo.Report;
import com.example.test_server.pojo.ReportFeedback;
import com.example.test_server.repository.ReportFeedbackService;
import com.example.test_server.repository.ReportService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ReportController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ReportService reportService;
    @Autowired
    private ReportFeedbackService reportFeedbackService;

    @CrossOrigin
    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public ResponseEntity<?> getReport(){
        Map<String, Object> sendBack = new HashMap<>(); // ส่งค่ากลับไปที่ Client
        try {
//            ArrayList<Report> rows = reportService.getReport();
//            ArrayList<ReportFeedback> rpl = reportFeedbackService.getReportFeedBack();
            Object Cast1 = rabbitTemplate.convertSendAndReceive("DircetGraceNotes", "getReport", "");
            Object Cast2 = rabbitTemplate.convertSendAndReceive("DircetGraceNotes", "getReportFeedBack", "");
            ArrayList<Report> rows = (ArrayList<Report>) Cast1;
            ArrayList<ReportFeedback> rpl = (ArrayList<ReportFeedback>) Cast2;
            sendBack.put("rp", rows);
            sendBack.put("rpl", rpl);
        }catch(Exception e){
            throw e;
        }
        return ResponseEntity.ok(sendBack);
    }

    @CrossOrigin
    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public ResponseEntity<?> createReport(@RequestBody HashMap<String, String> formData){
        Map<String, Object> sendBack = new HashMap<>(); // ส่งค่ากลับไปที่ Client
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Report result = reportService.createReport(new Report(null, formData.get("head"), formData.get("body"), formData.get("sid"), ""+timestamp));
            if (result == null) {
                sendBack.put("status", false);
                sendBack.put("message", "ส่งรายงานไม่สำเร็จ");
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
