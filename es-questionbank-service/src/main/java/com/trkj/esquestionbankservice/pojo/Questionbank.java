package com.trkj.esquestionbankservice.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Questionbank {
    private int questionbankid;
    private String qbname;
    private String qbclassification;
    private String qbdescription;
    private int qbnumber;
    private Date qbtime;
}
