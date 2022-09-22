package com.trkj.user.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class TquestionsEntity {
    private int testid;//id
    private String tcontent;//试题内容
    private String tqtype;//试题类型
    private String tqlevel;//试题难度等级
    private Timestamp tqtime;//创建时间
    private int questionbankid;//题库id
    private String qbname;//题库名称
    private int con;//统计
    private List<String> answers;//试题选项内容
    private List<String> isanswers;//是否为答案
//    private String
//    private List<Answers> answers;

}
