package com.example.test_server.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document("comment")
public class Comment {

    @Id
    private String _id;
    private String comment_detail;
    private String member_id;
    private String social_id;
    private String comment_timestamp;

    public Comment(String _id, String comment_detail, String member_id, String social_id, String comment_timestamp) {

        this._id = _id;
        this.comment_detail = comment_detail;
        this.member_id = member_id;
        this.social_id = social_id;
        this.comment_timestamp =comment_timestamp;
    }
}
