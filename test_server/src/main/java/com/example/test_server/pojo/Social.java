package com.example.test_server.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


@Data
@Document("social")

public class Social implements Serializable {

    @Id
    private String _id;
    private String social_detail;
    private String social_img;
    private String member_id;
    private String social_timestamp;

    public Social(String _id, String social_detail, String social_img, String member_id, String social_timestamp) {

        this._id = _id;
        this.social_detail = social_detail;
        this.social_img = social_img;
        this.member_id = member_id;
        this.social_timestamp = social_timestamp;


    }

}

