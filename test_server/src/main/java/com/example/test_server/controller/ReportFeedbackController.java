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
        try {
            boolean status = reportFeedbackService.deleteReportFeedBack(id);
            return ResponseEntity.ok(status);
        }catch(Exception e){
            return ResponseEntity.ok(e);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/reply", method = RequestMethod.POST)
    public ResponseEntity<?> createReply(@RequestParam("detail") String detail,
                                         @RequestParam("sid") String sid,
                                         @RequestParam("uid") String uid){
        try {
            ReportFeedback newReply = reportFeedbackService.createReportFeedBack(
                    new ReportFeedback(null, sid, detail, uid, new Timestamp(new Date().getTime()).toString()
                    ));
            return ResponseEntity.ok(newReply);
        }catch(Exception e){
            return ResponseEntity.ok(e);
        }
    }
}
