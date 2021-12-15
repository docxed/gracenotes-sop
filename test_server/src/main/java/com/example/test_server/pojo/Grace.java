package com.example.test_server.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document("grace")
public class Grace {

    @Id
    private String _id;
    private String grace_time;
    private String grace_date;
    private String grace_detail;
    private String grace_agency;
    private String grace_img;
    private String grace_check;
    private String member_id;
    private String member_fname;
    private String member_lname;
    private String grace_timestamp;

    public Grace(){}

    public Grace(String _id, String grace_time, String grace_date, String grace_detail, String grace_agency, String grace_img, String grace_check, String member_id, String member_fname, String member_lname, String grace_timestamp) {
        this._id = _id;
        this.grace_time = grace_time;
        this.grace_date = grace_date;
        this.grace_detail = grace_detail;
        this.grace_agency = grace_agency;
        this.grace_img = grace_img;
        this.grace_check = grace_check;
        this.member_id = member_id;
        this.member_fname = member_fname;
        this.member_lname = member_lname;
        this.grace_timestamp = grace_timestamp;
    }
}
