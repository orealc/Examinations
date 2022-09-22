package com.trkj.esexpaperservice.service;

import com.github.pagehelper.PageInfo;
import com.trkj.esexpaperservice.entity.Questionbank;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionbankService {
//    List<Questionbank>selAll();
    int tkxz(Questionbank questionbank);
    //分页条件查询
    PageInfo<Questionbank> selname(String qbname, int pageNum, int pageSize);
    //根据id查询所有
    Questionbank findtkid(int questionbankid);
    Questionbank uptikus(Questionbank questionbank);
}
