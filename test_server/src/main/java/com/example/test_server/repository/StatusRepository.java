package com.example.test_server.repository;

import com.example.test_server.pojo.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface StatusRepository extends MongoRepository<Status, String> {

    @Query(value = "{social_id: '?0'}")
    public ArrayList<Status> getStatusBySocialId(String social_id);

    @Query(value = "{social_id: '?0', member_id: '?1'}")
    public ArrayList<Status> getStatusForDelete(String sid, String uid);

}
