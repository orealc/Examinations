package com.trkj.user.entity;

import lombok.Data;

import java.io.Serializable;

//试题答案中间表
@Data
public class Answers {
    private String isanswers;//是否为答案
    private String optioncontent;//选项内容

}
