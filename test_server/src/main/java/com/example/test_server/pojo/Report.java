package com.example.test_server.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("report")
public class Report {
    @Id
    private String _id;
    private String report_topic;
    private String report_detail;
    private String member_id;
    private String report_timestamp;

    public Report(){}
    public Report(String _id, String report_topic, String report_detail, String member_id, String report_timestamp){
        this._id = _id;
        this.report_topic = report_topic;
        this.report_detail = report_detail;
        this.member_id = member_id;
        this.report_timestamp = report_timestamp;
    }
}
