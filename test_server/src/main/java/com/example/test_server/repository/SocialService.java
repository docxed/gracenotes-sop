package com.example.test_server.repository;
import com.example.test_server.pojo.Social;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class SocialService {

    @Autowired
    private SocialRepository repository;

    public SocialService(SocialRepository repository) {
        try{this.repository = repository;}catch(Exception e){throw  e;}
    }

    public List<Social> getSocial() {
        try{return repository.findAll(Sort.by(Sort.Direction.ASC, "_id"));}catch(Exception e){throw e;}
    }

    public Social getSocial(String id) {
        try{return repository.findWithId(id);}catch(Exception e){throw e;}
    }

    public void deleteSocial(String id){

        try{repository.deleteById(id);}catch(Exception e){throw e;}

    };

    public Social addSocial(Social social){



        try{return repository.save(social);}catch(Exception e){throw e;}


    }

    public Social updateSocial(Social social){

        try{
            Social target = repository.findWithId(social.get_id());
            Social New = new Social(target.get_id(), social.getSocial_detail(), target.getSocial_img(), target.getMember_id(), target.getSocial_timestamp());
            return repository.save(New);

        }catch(Exception e)
        {throw e;}


    }


}
