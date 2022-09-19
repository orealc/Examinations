package com.trkj.user.entity;

import lombok.Data;

@Data
public class TestpaperEntity {
    private int testpaperid;
    private int testid;
    private String testname;
    private String testclassification;
    private String testmethod;
    private int questionsnum;
    private double questionscore;
}
