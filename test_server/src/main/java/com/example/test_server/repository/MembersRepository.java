package com.example.test_server.repository;

import com.example.test_server.pojo.Members;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MembersRepository extends MongoRepository<Members, String> {

    @Query(value = "{_id: '?0'}")
    public Members findWithId(String id);
}
