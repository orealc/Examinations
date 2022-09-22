package com.trkj.user.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Record {
    private int recordid;
    private int userid;
    private String testname;
    private int highestscore;
    private Date recenttime;
    private String realname;
}
