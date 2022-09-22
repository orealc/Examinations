package com.trkj.esexpaperservice.entity;

import lombok.Data;

import java.util.Date;

//试题表
@Data
public class Tquestions {
    private int testid;
    private int questionbankid;
    private String tcontent;
    private String tqtype;
    private String tqlevel;
    private Date tqtime;
    //    题库名称
    private String qbname;
}
