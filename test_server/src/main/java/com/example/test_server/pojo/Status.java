package com.example.test_server.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("status")
public class Status {
    @Id
    private String _id;
    private String status_type;
    private String member_id;
    private String social_id;
    private String status_timestamp;

    public Status(){}
    public Status(String _id, String status_type, String member_id, String social_id, String status_timestamp){
        this._id = _id;
        this.status_type = status_type;
        this.member_id = member_id;
        this.social_id = social_id;
        this.status_timestamp = status_timestamp;
    }

}
