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
        ArrayList<Report> rows = reportService.getReport();
        ArrayList<ReportFeedback> rpl = reportFeedbackService.getReportFeedBack();
        Map<String, Object> allData = new HashMap<>();
        allData.put("0", rows);
        allData.put("1", rpl);
        return ResponseEntity.ok(allData);
    }

    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public ResponseEntity<?> createReport(@RequestParam("head") String head,
                                          @RequestParam("body") String body,
                                          @RequestParam("sid") String sid){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Timestamp(date.getTime()));
        Report result = reportService.createReport(new Report(null, head, body, sid, time));
        return ResponseEntity.ok(result);
    }
}
