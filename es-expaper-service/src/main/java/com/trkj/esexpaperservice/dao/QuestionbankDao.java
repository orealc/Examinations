package com.trkj.esexpaperservice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.trkj.esexpaperservice.entity.Questionbank;
import com.trkj.esexpaperservice.entity.Tquestions;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface QuestionbankDao {
    int tkxz(Questionbank questionbank);
    List<Questionbank> selname(String qbname);
    Questionbank findtkid(int questionbankid);
    int uptikus(Questionbank questionbank);
}
