package com.example.test_server.repository;

import com.example.test_server.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository repository;

    public CommentService(CommentRepository repository) {
        this.repository = repository;
    }

    public List<Comment> getComment() {

        try{return repository.findAll(Sort.by(Sort.Direction.ASC, "_id"));}catch(Exception e){throw e;}

    }

    public Comment getComment(String id) {

        try{return repository.findWithId(id);}catch(Exception e){throw e;}

    }


    public void deleteComment(String id){

        try{repository.deleteById(id);}catch(Exception e){throw e;}

    };

    public Comment addComment(Comment comment){


        try{return repository.save(comment);}catch(Exception e){throw e;}


    }
}
