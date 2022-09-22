package com.trkj.esexpaperservice.service;

import com.github.pagehelper.PageInfo;
import com.trkj.esexpaperservice.entity.Tquestions;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TquestionsService {
    PageInfo<Tquestions> eqselname(@Param("tcontent") String tcontent,int pageNum, int pageSize);
    int insst(Tquestions tquestions);
    Tquestions findid(int testid);
    List<Tquestions> selstname(int questionbankid);
    Tquestions uptquestion(Tquestions tquestions);
}
