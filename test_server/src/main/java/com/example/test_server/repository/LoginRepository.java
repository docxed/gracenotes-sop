package com.example.test_server.repository;

import com.example.test_server.pojo.Members;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends MongoRepository<Members, String> {

    @Query(value = "{member_user: ?0, member_password: ?1}")
    public Members login(String user, String pass);

}
