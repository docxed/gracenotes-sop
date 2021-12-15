package com.example.test_server.repository;

import com.example.test_server.pojo.Status;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StatusService {
    @Autowired
    private StatusRepository statusRepository;

    public StatusService(){}
    @RabbitListener(queues = "getStatusFromSocialId")
    public ArrayList<Status> getStatusFromSocialId(String id){
        return statusRepository.getStatusBySocialId(id);
    }
    public Status createStatus(Status status){
        try {
            ArrayList<Status> deleteStatus = statusRepository.getStatusForDelete(status.getSocial_id(), status.getMember_id());
            statusRepository.deleteAll(deleteStatus);
            return statusRepository.save(status);
        } catch (Exception e) {
            throw e;
        }
    }
    public boolean deleteStatus(String sid, String uid){
        try {
            ArrayList<Status> deleteStatus = statusRepository.getStatusForDelete(sid, uid);
            statusRepository.deleteAll(deleteStatus);
            return true;
        }catch(Exception e){
            throw e;
        }
    }
}
