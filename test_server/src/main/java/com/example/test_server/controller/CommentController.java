package com.example.test_server.controller;

import com.example.test_server.pojo.Comment;
import com.example.test_server.repository.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
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
    public ResponseEntity<?> addComment(@RequestParam("detail") String detail, @RequestParam("mem_id") String member_id, @RequestParam("social_id") String social_id) {

        try{
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Comment comment = new Comment(null, detail, member_id, social_id, timestamp+"");

            commentService.addComment(comment);
            return ResponseEntity.ok(comment);}catch(Exception e){throw e;}


    }


}