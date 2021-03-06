package com.example.test_server.repository;

import com.example.test_server.pojo.Members;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MembersService {
    @Autowired
    private MembersRepository repository;

    public MembersService(MembersRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = "getMember")
    public List<Members> getMembers() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "member_user"));
    }
    public Members getMember(String id) {
        return repository.findWithId(id);
    }
    public Members update(Members member){
        return repository.save(member);
    }
    public Members updateLevel(Members member) {
        return repository.save(member);
    }
    public Boolean deleteMember(String id) {
        try {
            repository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
