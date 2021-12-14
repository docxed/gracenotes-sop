package com.example.test_server.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("report_feedback")
public class ReportFeedback {
    @Id
    private String _id;
    private String report_id;
    private String reply_detail;
    private String member_id;
    private String reply_timestamp;

    public ReportFeedback(){}
    public ReportFeedback(String _id, String report_id, String reply_detail, String member_id, String reply_timestamp){
        this._id = _id;
        this.report_id = report_id;
        this.reply_detail = reply_detail;
        this.member_id = member_id;
        this.reply_timestamp = reply_timestamp;
    }
}
