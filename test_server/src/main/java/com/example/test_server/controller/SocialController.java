package com.example.test_server.controller;

import com.example.test_server.pojo.Grace;
import com.example.test_server.pojo.Social;
import com.example.test_server.repository.SocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
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
        try{socialService.deleteSocial(id);
            return ResponseEntity.ok("Delete social id:"+id);
        }catch(Exception e){
            throw e;
        }

    }
    @CrossOrigin
    @RequestMapping(value = "/social", method = RequestMethod.POST)
    public ResponseEntity<?> addSocial(@RequestParam("detail") String detail, @RequestParam("img") String img, @RequestParam("mem_id") String member_id) {

        try{Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Social social = new Social(null, detail, img, member_id, timestamp+"");

            socialService.addSocial(social);
            return ResponseEntity.ok(social);}catch(Exception e){throw e;}


    }

    @CrossOrigin
    @RequestMapping(value = "/social", method = RequestMethod.PUT)
    public ResponseEntity<?> updateSocial(@RequestParam("_id") String _id, @RequestParam("detail") String detail) {

        try{
            Social target = socialService.getSocial(_id);
            Social NewSocial = new Social(target.get_id(), detail, target.getSocial_img(), target.getMember_id(), target.getSocial_timestamp());

            socialService.updateSocial(NewSocial);
            return ResponseEntity.ok(NewSocial);}catch(Exception e){throw e;}

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
