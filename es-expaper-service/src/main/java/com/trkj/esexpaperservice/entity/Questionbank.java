package com.trkj.esexpaperservice.entity;

import lombok.Data;

import java.util.Date;
//题库表
@Data
public class Questionbank {
    private int questionbankid;
    private String qbname;
    private String qbclassification;
    private String qbdescription;
    private int qbnumber;
    private Date qbtime;
}
