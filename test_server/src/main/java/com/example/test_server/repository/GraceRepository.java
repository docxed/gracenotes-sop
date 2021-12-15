package com.example.test_server.repository;


import com.example.test_server.pojo.Grace;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GraceRepository extends MongoRepository<Grace, String> {

    @Query(value = "{_id: '?0'}")
    public Grace findWithId(String id);

}
