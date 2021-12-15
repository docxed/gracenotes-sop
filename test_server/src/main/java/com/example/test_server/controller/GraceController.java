package com.example.test_server.controller;

import com.example.test_server.pojo.Comment;
import com.example.test_server.pojo.Grace;
import com.example.test_server.pojo.Social;
import com.example.test_server.repository.GraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
@RestController
public class GraceController {

    @Autowired
    private GraceService graceService;

    @CrossOrigin
    @RequestMapping(value = "/grace", method = RequestMethod.GET)
    public ResponseEntity<?> getGrace() {
        try{
            List<Grace> grace = graceService.getGrace();
            return ResponseEntity.ok(grace);
        }catch(Exception e){
            throw e;
        }

    }

    @CrossOrigin
    @RequestMapping(value = "/grace/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getGrace(@PathVariable("id") String id) {
        Grace grace = graceService.getGrace(id);

        try {
            if (grace == null) {
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
    public ResponseEntity<?> addGrace(@RequestParam("time") String grace_time, @RequestParam("date") String grace_date, @RequestParam("detail") String grace_detail, @RequestParam("agency") String grace_agency, @RequestParam("img") String grace_img, @RequestParam("check") String grace_check, @RequestParam("mem_id") String member_id) {

        try{

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            Grace grace = new Grace(null, grace_time, grace_date, grace_detail, grace_agency, grace_img, grace_check, member_id, timestamp+"");

            graceService.addGrace(grace);
            return ResponseEntity.ok(grace);}catch(Exception e){throw e;}


    }

    @CrossOrigin
    @RequestMapping(value = "/grace", method = RequestMethod.PUT)
    public ResponseEntity<?> updateGrace(@RequestParam("_id") String _id, @RequestParam("check") String grace_check) {

        try{


            Grace target = graceService.getGrace(_id);

            Grace NewGrace = new Grace(target.get_id(), target.getGrace_time(), target.getGrace_date(), target.getGrace_detail(), target.getGrace_agency(), target.getGrace_img(), grace_check, target.getMember_id(), target.getGrace_timestamp());

            graceService.updateGrace(NewGrace);
            return ResponseEntity.ok(NewGrace);}catch(Exception e){throw e;}


    }

    @CrossOrigin
    @RequestMapping(value = "/grace/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteComment(@PathVariable("id") String id) {
        try{graceService.deleteGrace(id);
            return ResponseEntity.ok("Delete grace id:"+id);
        }catch(Exception e){
            throw e;
        }

    }
}
