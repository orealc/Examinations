package com.trkj.user.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ExaminationEntity implements Serializable {
        private int examinationid;
        private int testpaperid;
        private String examname;
        private String examtype;
        private String kssc;
        private int questionscore;
}
