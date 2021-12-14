package com.example.test_server.repository;

import com.example.test_server.pojo.Members;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    @Autowired
    private RegisterRepository repository;

    public RegisterService(RegisterRepository repository) {
        this.repository = repository;
    }

    public Members register(Members member) {
        return this.repository.save(member);
    }

    public boolean checkDupped(String id) {
        Members member = this.repository.findWithId(id);
        boolean status;
        if (member == null) { // ไม่มี user คนไหนซ้ำกับคนนี้ (พร้อมที่จะ register)
            status = true;
        }else{
            status = false;
        }
        return status;
    }


}
