package com.trkj.user.vo;

import com.trkj.user.entity.Answers;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SjstzjbVo implements Serializable {
    private int sjstid;
    private int testpaperid;
    private int testid;
    private int tj;//统计
    private int questionscore;//试题总分
    private String tcontent;//试题内容
    private List<String> answers;//试题选项内容

}
