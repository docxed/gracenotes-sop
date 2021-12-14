package com.example.test_server.repository;

import com.example.test_server.pojo.ReportFeedback;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportFeedbackRepository extends MongoRepository<ReportFeedback, String> {

    @Query(value="{_id: '?0'}")
    public ReportFeedback getReplyById(String _id);
}
