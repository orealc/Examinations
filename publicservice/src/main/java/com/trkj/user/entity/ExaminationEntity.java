package com.trkj.user.entity;

import lombok.Data;

@Data
public class ExaminationEntity {
        private int examinationid;
        private int testpaperid;
        private String examname;
        private String examtype;
        private double totalscore;
}
