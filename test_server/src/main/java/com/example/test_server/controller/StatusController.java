package com.example.test_server.controller;

import com.example.test_server.pojo.Status;
import com.example.test_server.repository.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

@RestController
public class StatusController {
    @Autowired
    private StatusService statusService;

    @CrossOrigin
    @RequestMapping(value = "/status/social/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getStatusFromSocialId(@PathVariable("id") String id){
        try {
            ArrayList<Status> status = statusService.getStatusFromSocialId(id);
            return ResponseEntity.ok(status);
        }catch(Exception e){
            throw e;
        }
    }

    @RequestMapping(value = "/status", method = RequestMethod.POST)
    public ResponseEntity<?> createStatus(@RequestParam("type") String type,
                                          @RequestParam("sid") String sid,
                                          @RequestParam("uid") String uid){
        try{
            Status newStatus = statusService.createStatus(
                    new Status(null, type, uid, sid, new Timestamp(new Date().getTime()).toString()));
            return  ResponseEntity.ok(newStatus);
        }catch(Exception e){
            throw e;
        }
    }

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
