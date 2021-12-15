package com.example.test_server.repository;

import com.example.test_server.pojo.ReportFeedback;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ReportFeedbackService {
    @Autowired
    private ReportFeedbackRepository reportFeedbackRepository;

    public ReportFeedbackService(ReportFeedbackRepository reportFeedbackRepository){
        this.reportFeedbackRepository = reportFeedbackRepository;
    }
    @RabbitListener(queues = "getReportFeedBack")
    public ArrayList<ReportFeedback> getReportFeedBack(){
        return (ArrayList<ReportFeedback>)reportFeedbackRepository.findAll(Sort.by(Sort.Direction.ASC, "reply_timestamp"));
    }
    public ReportFeedback createReportFeedBack(ReportFeedback reportFeedback){
        return reportFeedbackRepository.save(reportFeedback);
    }

    public Boolean deleteReportFeedBack(String id){
        try {
            ReportFeedback deleteFeedback = reportFeedbackRepository.getReplyById(id);
            reportFeedbackRepository.delete(deleteFeedback);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
