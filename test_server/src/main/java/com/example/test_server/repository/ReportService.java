package com.example.test_server.repository;

import com.example.test_server.pojo.Report;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository){
        this.reportRepository = reportRepository;
    }
    @RabbitListener(queues = "getReport")
    public ArrayList<Report> getReport(){
        return (ArrayList<Report>) reportRepository.findAll(Sort.by(Sort.Direction.ASC, "report_timestamp"));
    }

    public Report createReport(Report report){
        return reportRepository.save(report);
    }


}
