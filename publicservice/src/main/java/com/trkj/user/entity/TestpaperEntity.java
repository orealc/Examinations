package com.trkj.user.entity;

import lombok.Data;

@Data
public class TestpaperEntity {
    private int testpaperid;
    private String testname;
    private String testclassification;
    private String testmethod;
    private int questionsnum;//统计试题数量
    private double questionscore;//试题总分

}
