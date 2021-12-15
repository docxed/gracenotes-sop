package com.example.test_server.repository;

import com.example.test_server.pojo.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StatusService {
    @Autowired
    private StatusRepository statusRepository;

    public StatusService(){}
    public ArrayList<Status> getStatusFromSocialId(String id){
        return statusRepository.getStatusBySocialId(id);
    }
    public Status createStatus(Status status){
        return statusRepository.save(status);
    }
    public boolean deleteStatus(String sid, String uid){
        try {
            ArrayList<Status> deleteStatus = statusRepository.getStatusForDelete(sid, uid);
            System.out.println(deleteStatus);
            statusRepository.deleteAll(deleteStatus);
            return true;
        }catch(Exception e){
            throw e;
        }
    }
}
