package com.example.test_server.repository;

import com.example.test_server.pojo.Comment;
import com.example.test_server.pojo.Social;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

    @Query(value = "{_id: '?0'}")
    public Comment findWithId(String id);

    @Query(value = "{social_id: '?0'}")
    public ArrayList<Comment> findWithSocialId(String id);

}
