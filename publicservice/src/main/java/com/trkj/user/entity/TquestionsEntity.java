package com.trkj.user.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class TquestionsEntity {
    private int testid;//id
    private String tcontent;//试题内容
    private String tqtype;//试题类型
    private String tqlevel;//试题难度等级
    private Timestamp tqtime;//创建时间
    private int questionbankid;//题库id
    private String qbname;//题库名称
}
