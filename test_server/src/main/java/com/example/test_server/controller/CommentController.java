package com.example.test_server.controller;

import com.example.test_server.pojo.Comment;
import com.example.test_server.repository.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @CrossOrigin
    @RequestMapping(value = "/comment", method = RequestMethod.GET)
    public ResponseEntity<?> getComment() {
        try{
            List<Comment> comment = commentService.getComment();
            return ResponseEntity.ok(comment);
        }catch(Exception e){
            throw e;
        }

    }

    @CrossOrigin
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getComment(@PathVariable("id") String id) {
        Comment comment = commentService.getComment(id);

        try {
            if (comment == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ไม่พบข้อมูล");
            } else {
                return ResponseEntity.ok(comment);
            }

        } catch (Exception e) {
            throw e;
        }
    }

    // JOIN
    @CrossOrigin
    @RequestMapping(value = "/comment/social/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCommentSocial(@PathVariable("id") String id) {
        ArrayList<Comment> comment = commentService.getCommentSocial(id);
        try {
            if (comment == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ไม่พบข้อมูล");
            } else {
                return ResponseEntity.ok(comment);
            }

        } catch (Exception e) {
            throw e;
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteComment(@PathVariable("id") String id) {
        try{commentService.deleteComment(id);
            return ResponseEntity.ok("Delete comment id:"+id);
        }catch(Exception e){
            throw e;
        }

    }

    @CrossOrigin
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public ResponseEntity<?> addComment(@RequestBody Map<String, String> formData) {
        Map<String, Object> sendBack = new HashMap<>(); // ส่งค่ากลับไปที่ Client
        try{
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Comment comment = commentService.addComment(new Comment(null, formData.get("detail"), formData.get("uid"), formData.get("member_fname"), formData.get("member_lname"), formData.get("sid"), ""+timestamp));
            if (comment == null){
                sendBack.put("message", "ส่งความคิดเห็นไม่สำเร็จ, โปรดลองอีกครั้ง");
                sendBack.put("status", false);
            }else {
                return ResponseEntity.ok(comment);
            }
        }catch(Exception e){
            throw e;
        }
        return ResponseEntity.ok(sendBack);

    }


}
