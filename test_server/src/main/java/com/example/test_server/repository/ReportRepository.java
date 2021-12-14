package com.example.test_server.repository;

import com.example.test_server.pojo.Report;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReportRepository extends MongoRepository<Report, String> {

}
