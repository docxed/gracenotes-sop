package com.example.test_server.repository;

import com.example.test_server.pojo.Grace;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GraceService {

    @Autowired
    private GraceRepository repository;

    public GraceService(GraceRepository repository) {
        try{this.repository = repository;}catch(Exception e){throw e;}

    }


    @RabbitListener(queues = "getGrace")
    public List<Grace> getGrace() {

        try{return repository.findAll(Sort.by(Sort.Direction.ASC, "_id"));}catch(Exception e){throw e;}

    }

    public Grace getGrace(String id) {

        try{return repository.findWithId(id);}catch(Exception e){throw e;}

    }

    public Boolean deleteGrace(String id){
        try{
            repository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }

    };

    public Grace addGrace(Grace grace){


        try{return repository.save(grace);}catch(Exception e){throw e;}


    }

    public Grace updateGrace(Grace grace){


        try{return repository.save(grace);}catch(Exception e){throw e;}


    }




}
