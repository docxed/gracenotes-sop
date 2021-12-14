package com.example.test_server.repository;

import com.example.test_server.pojo.Social;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialRepository extends MongoRepository<Social, String> {

    @Query(value = "{_id: '?0'}")
    public Social findWithId(String id);




}

