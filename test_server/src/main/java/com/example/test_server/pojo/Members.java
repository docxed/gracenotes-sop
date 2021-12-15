package com.example.test_server.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document("members")
public class Members implements Serializable {
    @Id
    private String _id;
    private String member_user;
    private String member_password;
    private String member_fname;
    private String member_lname;
    private String member_class;
    private String member_no;
    private String member_dob;
    private String member_address;
    private String member_img;
    private String member_level;
    private String member_timestamp;

    public Members() {}

    public Members(String _id, String member_user, String member_password, String member_fname, String member_lname, String member_class, String member_no, String member_dob, String member_address, String member_img, String member_level, String member_timestamp) {
        this._id = _id;
        this.member_user = member_user;
        this.member_password = member_password;
        this.member_fname = member_fname;
        this.member_lname = member_lname;
        this.member_class = member_class;
        this.member_no = member_no;
        this.member_dob = member_dob;
        this.member_address = member_address;
        this.member_img = member_img;
        this.member_level = member_level;
        this.member_timestamp = member_timestamp;
    }
}
