package com.trkj.esexpaperservice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.trkj.esexpaperservice.entity.Tquestions;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TquestionsDao extends BaseMapper<Tquestions> {
    List<Tquestions> eqselname(@Param("tcontent") String tcontent);
    int insst(Tquestions tquestions);
    Tquestions findid(int testid);
    List<Tquestions> selstname(int questionbankid);
    int uptquestion(Tquestions tquestions);
}
